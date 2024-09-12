package scopeFunctions

class ScopeFunctionTable {
    /*
        Function        |Access to x via| Return Value
        ___________________________________________________
        x.let {..}      |it             |  Result of Lambda
        x.also {..}     |it             |  x
        x.apply {..}    |this           |  x
        x.run {..}      |this           |  Result of Lambda
        with(x) {..}    |this           |  Result of Lambda

     */
}