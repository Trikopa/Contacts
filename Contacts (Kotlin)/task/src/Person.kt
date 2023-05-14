package contacts

data class Person(val name: String, val surname: String, val number: String) {
    override fun toString(): String {
        return "$name $surname, $number"
    }
}
