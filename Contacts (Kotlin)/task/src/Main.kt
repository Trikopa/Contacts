package contacts

fun main(args: Array<String>) {
    val phoneBook = PhoneBook(getFileFromArg(args.getOrNull(0)?: "wow"))
    phoneBook.action()
}

