import java.io.File

fun readFromFile(fileName: String): String {
    val file = File(fileName)
    return file.readText()
}
fun encryptWord(word: String, offset: Int): String {
    val encryptedChars = word.map { char ->
        if (char.isLetter()) {
            val baseAscii = if (char.isLowerCase()) 'a'.toInt() else 'A'.toInt()
            val encryptedAscii = (baseAscii + (char.toInt() - baseAscii + offset) % 26).toChar()
            encryptedAscii
        } else {
            char
        }
    }
    return encryptedChars.joinToString("")
}

fun encryptWordsInRange(text: String, offset: Int): String {
    val words = text.split("\\s+".toRegex())
    val encryptedWords = words.map { word ->
        if (word.length in 4..7) {
            encryptWord(word, offset)
        } else {
            word
        }
    }
    return encryptedWords.joinToString(" ")
}
fun main() {
    val fileName = "D:\\scoala\\PP\\Exercitiu PP\\Tema11\\a.txt"
    val offset = 3

    val inputText = readFromFile(fileName)
    val encryptedText = encryptWordsInRange(inputText, offset)

    println("Textul criptat:\n$encryptedText")
}
