package contacts

import kotlinx.serialization.json.Json
import java.io.File

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


fun ask(message: String): String {
    print(message)
    return readln()
}

fun multipleAsk(messages: List<String>, errorMessages: List<String> = listOf()): List<String> {
    val newData = mutableListOf<String>()
    for (i in messages.indices) {
        print(messages[i])
        newData.add(genericParser(readln(), errorMessages[i]))
    }
    return newData
}


fun searchRecords(records: List<Wrapper>, query: String): MutableSet<Int> {
    val indexesOfResults = mutableSetOf<Int>()
    for ((i, e) in records.withIndex()) {
        for (j in e.searchForm()) {
            if (j.lowercase().contains(query.lowercase())) {
                indexesOfResults.add(i)
            }
        }
    }
    return indexesOfResults
}



fun getFileFromArg(fileName: String): MutableList<Wrapper> {
    try {
        val file = File(fileName)
        return if (file.exists()) {
            Json.decodeFromString<MutableList<Wrapper>>(file.readText())
        } else {
            mutableListOf()
        }
    } catch (e: Exception) {
        println("Error occurred while accessing file '$fileName': ${e.message}")
    }
    return mutableListOf()
}
