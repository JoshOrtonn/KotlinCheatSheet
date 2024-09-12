package variance

interface Money {}
interface Coin: Money {}
class Dime: Coin {}
interface Product
interface Snack: Product {
    companion object {
        fun random(): Snack {
            TODO("Not yet implemented")
        }
    }
}
class CandyBar: Snack