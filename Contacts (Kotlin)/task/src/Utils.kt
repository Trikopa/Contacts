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

fun genericParser(string: String, error: String): String {
    if (error == "Bad gender") {
        return genderParse(string)
    }

    if (error == "Wrong number format!") {
        return phoneNumberChecker(string)
    }

    if (string.isEmpty()) {
        println(error)
        return "[no data]"
    }
    return string
}


fun genderParse(gender: String): String {
    if (gender != "M" && gender != "F") {
        println("Bad gender!")
        return "[no data]"
    }
    return gender
}