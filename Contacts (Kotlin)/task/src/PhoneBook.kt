package contacts


fun ask(message: String): String {
    print(message)
    return readln()
}

data class PhoneBook(val personList: MutableList<Person> = mutableListOf()) {

    fun action() {
        while (true) {
            when (ask("Enter action (add, remove, edit, count, list, exit): ")) {
                "add" -> add()
                "remove" -> remove()
                "edit" -> edit()
                "count" -> count()
                "list" -> list()
                "exit" -> break
            }
        }
    }

    private fun add() {
        val name = ask("Enter the name:")
        val surname = ask("Enter the surname:")
        val number = phoneNumberChecker( ask("Enter the number:"))
        personList.add(Person(name, surname, number)).also { println("The record added.") }
    }

    private fun edit() {
        if (personList.isEmpty()) {
            println("No records to edit!")
            return
        }
        list()
        val record = ask("Select a record:").toInt() - 1
        when(ask("Select a field (name, surname, number): ")) {
            "name" -> personList[record] = personList[record].copy(name = ask("Enter name: ")).also { println("The record updated!") }
            "surname" -> personList[record] = personList[record].copy(surname = ask("Enter surname: ")).also { println("The record updated!") }
            "number" ->  {
                val number = ask("Enter number: ")
                personList[record] = personList[record].copy(number = phoneNumberChecker(number))
                println("The record updated!")
            }
            else -> println("Invalid")
        }
    }

    private fun remove() {
        if (personList.isEmpty()) {
            println("No records to remove!")
            return
        }
        list()
        personList.removeAt(ask("Select a record:").toInt() - 1)
        println("The record removed!")

    }
    private fun count() {
        println("The Phone Book has ${personList.size} records.")
    }
    private fun list() {
        for ((i,e) in personList.withIndex()) {
            println("${i + 1}. $e")
        }
    }
}
