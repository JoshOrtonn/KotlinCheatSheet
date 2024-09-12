package classes

import java.nio.file.Path
import kotlin.io.path.readLines

class LocalClass {
    fun printGrades(filePath: Path) {
        // Local kotlin.classes are useful as helper class that's only
        // relevant to impl of the function.
        class ExamResult(val student: String, val score: Int){
            fun grade() = when {
                score > 92 -> "A"
                score > 85 -> "B"
                score > 72 -> "C"
                score > 63 -> "D"
                score > 50 -> "E"
                else -> "F"
            }
        }

        filePath.readLines()
            .map { it.split(" ") }
            .map { ExamResult(it[0], it[1].toInt()) }
            .forEach { println("Grade for ${it.student} is ${it.score}") }
    }
    fun exampleMain() {
        printGrades(filePath = Path.of("src/main/something.txt"))
    }
}

