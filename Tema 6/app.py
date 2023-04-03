import os
import re
import struct

class GenericFile:
    def get_path(self):
        raise NotImplementedError("`get_path` must be implemented")

    def get_freq(self):
        raise NotImplementedError("`get_path` must be implemented")



class TextASCII(GenericFile):
    def __init__(self, path, frequency):
        self.path_absolut = path
        self.frecventa = frequency

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecventa


class TextUNICODE(GenericFile):
    def __init__(self, path, frequency):
        self.path_absolut = path
        self.frecventa = frequency

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecventa


class Binary(GenericFile):
    def __init__(self, path, frequency):
        self.path_absolut = path
        self.frecventa = frequency

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecventa


class XMLFile(TextASCII):
    def __init__(self, path, freq, first_tag):
        super().__init__(path, freq)
        self.first_tag = first_tag

    def get_first_tag(self):
        return self.first_tag


class BMP(Binary):
    def __init__(self, path, frequency, width, height, bpp):
        super().__init__(path, frequency)
        self.width = width
        self.height = height
        self.bpp = bpp

    def show_info(self):
        print(self.width + ' ' + self.height + ' ' + self.bpp)
if __name__ == '__main__':

    ROOT_Dir = os.path.dirname(os.path.abspath(__file__))
    file_list = []
    for root, subdirs, files, in os.walk(ROOT_Dir):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                f = open(file_path, 'rb')
                try:
                    frequency = [0]*256
                    content = f.read()
                    hex_content = content.hex()
                    hex_list = re.findall('..',hex_content)
                    for hex_pair in hex_list:
                        hex_num = int(hex_pair,16)
                        frequency[hex_num] += 1
                    total = sum(frequency)
                    if frequency[0] / (total + 0.1) > 0.3:
                        file_list.append(TextUNICODE(file_path, frequency))

                    else:
                        if sum(frequency[32:127]) + frequency[9] + frequency[10] + frequency[13] > sum(frequency[0:8]) + sum(frequency[11:12]) + sum(frequency[14:31]) + sum(frequency[128:255]):
                                index1 = content.find(b"<")
                                index2 = content.find(b">")
                                first_tag = content[index1:index2]
                                file_list.append(XMLFile(file_path, frequency, first_tag))
                finally:
                    f.close()