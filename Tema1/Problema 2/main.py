
f = open("data.txt", "r")
change = f.read()
for repl in [".", ",", "?","!", "-", "_",":", ";", "/","(", ")", "'",'"']:
    change = change.replace(repl,"")
change = " ".join(change.split())
print(change.upper())
print(change.lower(), end='')


f.close()