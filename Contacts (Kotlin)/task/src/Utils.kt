package contacts

fun phoneNumberChecker(phoneNumber: String): String {
    val regex = Regex("\\+?\\d*\\s*(\\(\\w{1,3}\\))\\s*(\\w+)?[-\\s](\\w{2,})?[-\\s](\\w{2,})?|" +
            "\\+?\\d*\\s*(\\w{1,3})\\s*(\\w+)?[-\\s](\\w{2,})?[-\\s](\\w{2,})?|\\w{1,3}[\\s-]*(\\w{1,3})" +
            "|(\\(\\w{1,3}\\))" + "|(\\(\\w{1,3}\\))[\\s-]*(\\w{1,3})" + "|(\\w{1,3})[\\s-]*(\\(\\w{1,3}\\))" +
            "|(\\w{1,3})[\\s-]*(\\(\\w{1,3}\\))[\\s-]*(\\w{1,3})" + "|\\+?(\\(\\w+\\))" + "|\\d" + "|\\+?\\d+[\\s-]*(\\w+)")

    return if (regex.matches(phoneNumber)) {
        phoneNumber
    } else {
        println("Wrong number format!")
        "[no number]"
    }
}