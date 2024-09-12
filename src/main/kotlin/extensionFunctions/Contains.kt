package extensionFunctions

import java.time.LocalDate
import java.time.Month
import java.time.Year

class Contains {
    fun oddNumberIn() {
        val oddNumbers = listOf(1,3,5,7,9,11)

        val number = 4

        // Secret to this in function is actually an operator function called contains
        if(number in oddNumbers) {
            println("Odd number")
        } else {
            println("Even number")
        }
    }

    fun localDateContainment(){
        val localDate = LocalDate.now()

        operator fun Month.contains(localDate: LocalDate): Boolean {
            return localDate.month === this
        }

        operator fun Int.contains(localDate: LocalDate): Boolean {
            return localDate.year === this
        }

        infix fun LocalDate.of(dateAndYear: Pair<Month, Int>): Boolean {
            return localDate in dateAndYear.first && localDate in dateAndYear.second
        }



        println(localDate in Month.FEBRUARY)
        println(localDate in 2024)
//        println(localDate in Month.SEPTEMBER of 2014)
    }
}