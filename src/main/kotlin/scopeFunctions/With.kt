package scopeFunctions

import scopeFunctions.Util.Canvas


// Group method calls without name repetition
// used in test method setup too
class With {

    fun with() {
        val canvas = Canvas()


        // Some canvas, where multiple objects, text can be created,
        // often duplicated multiple times
//        canvas.text(10, 10, "10")
//        canvas.circ(10, 10, 10, 10)
//        canvas.rect(10, 10, 10, 10)
//        canvas.text(10, 10, "10")
//        canvas.circ(10, 10, 10, 10)
//        canvas.rect(10, 10, 10, 10)
//        canvas.text(10, 10, "10")
//        canvas.circ(10, 10, 10, 10)
//        canvas.rect(10, 10, 10, 10)

        // So subsititute is using .with {} to group method calls
        // and to neaten up the redundancy of name repetition
        with(canvas) {
            text(10, 10, "10")
            circ(10, 10, 10, 10)
            rect(10, 10, 10, 10)
            text (10, 10, "10")
            circ(10, 10, 10, 10)
            rect(10, 10, 10, 10)
            text (10, 10, "10")
            circ(10, 10, 10, 10)
            rect(10, 10, 10, 10)
        }


    }
}