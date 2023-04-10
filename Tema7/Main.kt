import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class HistoryLogRecord(val timestamp: Long, val commandLine: String): Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int {
        if(this.timestamp < other.timestamp) {
            return -1
        }
        else if(this.timestamp > other.timestamp) {
            return 1
        }
        else {
            return 0
        }
    }

    override fun toString(): String {
        return "HistoryLogRecord(timestamp=$timestamp, commandLine='$commandLine')"
    }
}

fun main() {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    val logRecords = mutableMapOf<Long, HistoryLogRecord>()
    var count = 0
    val file = File("/home/cristi/Facultate/Exercitiu PP/tema7/history.log")
    file.readLines().asReversed().forEach { line ->
        if (count == 50) return@forEach
        var startTimestamp : Long = 0
        var commandLine = ""
        when {
            line.startsWith("Start-Date:") -> {
                startTimestamp = dateFormat.parse(line.substringAfter(": ").trim()).time
            }
            line.startsWith("Commandline:") -> {
                 commandLine = line.substringAfter(": ").trim()

            }
        }
        logRecords[startTimestamp] = HistoryLogRecord(startTimestamp, commandLine)
        count++
    }
    println(logRecords)

    val maxRecord = getMaxRecord(logRecords.values.elementAt(1), logRecords.values.elementAt(2))
    println("Max record: $maxRecord")
}

fun <T : Comparable<T>> getMaxRecord(first : T, second : T): T {
    if(first == null)
        return second
    else if(second == null)
        return first
    else if(first > second)
        return first
    else
        return second
}
