import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog
from PyQt5.uic import loadUi
from PyQt5 import QtCore


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    # QtCore.pyqtRestoreInputHook()



class HTMLConverter(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))
    def text_to_html(self, ui):
        with open(self.file_path, "r") as file:
            file_text = file.read()

        paragraphs = file_text.split("\n")

        html_change = "<html>\n<head>\n<title>{}</title>\n</head>\n<body>".format(file_text)
        for paragraph in paragraphs:
            html_change += "\n<p>{}</p>".format(paragraph)
        html_change += "\n</body>\n</html>".format(file_text)
            
        self.plainTextEdit.setPlainText(html_change)        

    def __init__(self):
        super(HTMLConverter, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'html_converter.ui')
        loadUi(ui_path, self)
        self.browse_btn.clicked.connect(self.browse)
        self.convert_html_btn.clicked.connect(self.text_to_html)
        self.file_path = None

    def browse(self):
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file, _ = QFileDialog.getOpenFileName(self,
                                              caption='Select file',
                                              directory='',
                                              filter="Text Files (*.txt)",
                                              options=options)
        if file:
            self.file_path = file
            self.path_line_edit.setText(file)
            print(file)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = HTMLConverter()
    window.show()
    sys.exit(app.exec_())
