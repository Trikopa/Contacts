package contacts

import java.time.LocalDateTime


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

class PhoneBook: Actions {
    private val listOfAll: MutableList<Wrapper> = mutableListOf()

    fun action() {
        while (true) {
            when (ask("Enter action (add, remove, edit, count, info, exit): ")) {
                "add" -> addWrapper()
                "remove" -> remove()
                "edit" -> edit()
                "count" -> count()
                "info" -> info()
                "exit" -> break
            }
        }
    }

    private fun addPerson() {
        val messages = listOf("Enter the name: ",
            "Enter the surname: ",
            "Enter the birth date: ",
            "Enter the gender (M, F): ",
            "Enter the number: ")
        val errorMessages = listOf("Bad name!", "Bad surname!", "Bad birth date!", "Bad gender!", "Wrong number format!")
        val (name, surname, birthDate, gender, number) = multipleAsk(messages = messages, errorMessages =  errorMessages)
        listOfAll.add(Person(
            name = name, surname =  surname, birthDate = birthDate, number = number,
            gender =  gender, timeCreated = LocalDateTime.now().toString()))
            .also { println("The record added.\n") }
    }

    private fun addOrganisation() {
        val messages = listOf(
            "Enter the organization name: ",
            "Enter the address: ",
            "Enter the number:"
            )
        val errorMessages = listOf("", "", "")
        val (name, address, number) = multipleAsk(messages = messages, errorMessages = errorMessages)
        listOfAll.add(Organization(name, address, phoneNumberChecker(number),
            timeCreated = LocalDateTime.now().toString()))
            .also { println("The record added.\n") }

    }

    private fun editOrganisation(record: Int) {
        val clone = listOfAll[record] as Organization
        when(ask("Select a field (address, number): ")) {
            "organisation name" -> listOfAll[record] = clone.copy(name = ask("Enter the organization name: "),
                timeLastEdited = LocalDateTime.now().toString() )
            "address" -> listOfAll[record] = clone.copy(address = ask("Enter the address: "),
                timeLastEdited = LocalDateTime.now().toString())
            "number" -> listOfAll[record] = clone.copy(phoneNumber = phoneNumberChecker(ask("Enter the number: ")),
                timeLastEdited = LocalDateTime.now().toString())
        }

    }

    private fun editPerson(record: Int) {
        val clone = listOfAll[record] as Person
        when(ask("Select a field (name, surname, birth, gender, number): ")) {
            "name" -> listOfAll[record] = clone.copy(name = ask("Enter name: "),
                timeLastEdited = LocalDateTime.now().toString())
                .also { println("The record updated!") }
            "surname" -> listOfAll[record] = clone.copy(surname = ask("Enter surname: "),
                timeLastEdited = LocalDateTime.now().toString())
                .also { println("The record updated!") }
            "number" ->  {
                val number = ask("Enter number: ")
                listOfAll[record] = clone.copy(number = phoneNumberChecker(number),
                    timeLastEdited = LocalDateTime.now().toString())
                println("The record updated!")
            }
            "birth" -> listOfAll[record] = clone.copy(birthDate = ask("Enter birth date: "),
                timeLastEdited = LocalDateTime.now().toString())
                .also { println("The record updated!") }
            "gender" -> listOfAll[record] = clone.copy(gender = ask("Enter the gender: "),
                timeLastEdited = LocalDateTime.now().toString())
                .also { println("The record updated!") }
            else -> println("Invalid")
        }
    }

    private fun remove() {
        if (listOfAll.isEmpty()) {
            println("No records to remove!")
            return
        }
        info()
        listOfAll.removeAt(ask("Select a record:").toInt() - 1)
        println("The record removed!")

    }
    private fun count() {
        println("The Phone Book has ${listOfAll.size} records.")
    }

    private fun list() {
        for ((i,e) in listOfAll.withIndex()) {
            println("${i + 1}. $e")
        }
    }
    private fun info() {
       list()
        val index = ask("Enter index to show info: ").toInt() - 1
        val rec = listOfAll[index]
        rec.infoForm()
        println()
    }

    override fun addWrapper() {
        when (ask("Enter the type (person, organization): ")) {
            "person" -> addPerson()
            "organization" -> addOrganisation()
            else -> println("Wrong type")
        }
    }

    override fun edit() {
        if (listOfAll.isEmpty()) {
            println("No records to edit!")
            return
        }
        list()
        val record = ask("Select a record:").toInt() - 1
        val rec = listOfAll[record]
        if (rec.isPerson) {
            editPerson(record)
        } else {
            editOrganisation(record)
        }
        println()
    }

    override fun removeWrapper() {

    }

    override fun listOfAll() {

    }
}
