package delegatedProperties

// Used to outsource specific behaviours to different kotlin.classes
// lazy
// Observable
// Vetoable
class DelegateDefinition {
    // Property associated with a class
    // You can override the behaviour of accessing that property
    // using custom access to do side effects during reading or setting a variable
    var foo: Int = 7
        get() {
            println("Thanks for getting!")
            return field
        }
        set(value) {
            println("Thanks for setting!")
            field = value
        }
}
