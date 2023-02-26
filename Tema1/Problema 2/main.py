f = open("data.txt", "r")
change = f.read()
for repl in [".", ",", "?","!", "-", "_",":", ";", "/","(", ")", "'",'"']:
    change = change.replace(repl,"")

print(change)

