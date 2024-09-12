package builderFunctions

class StringBuilderExample {

    fun createStringWithBuilderFunction(): String {
        val str = buildString {
            append("Josh")
            appendLine()
            append(" is ")
            appendLine()
            append("Cool")
        }

        return str
    }

    fun createString(): String {
        val sb = StringBuilder()
//        with(sb){
//            append("Josh ")
//            append("is ")
//            append("Cool")
//        }

        sb.apply {
            sb.append("Josh ")
            sb.appendLine()
            sb.append("is ")
            sb.appendLine()
            sb.append("Cool")
        }

        val stringOutput = sb.toString()

        return stringOutput
    }

}