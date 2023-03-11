import java.io.File

fun main() {
    val file = File("/home/cristi/Facultate/Exercitiu PP/PP tema 3/a.txt").readText()
    val newLine = file.replace(Regex("\\n+"), "\n")
    val newSpaces = newLine.replace(Regex(" +"), " ")
    val NonrPages = newSpaces.replace(Regex("pg \\d+"), "")
    println(NonrPages)

}