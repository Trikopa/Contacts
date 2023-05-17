package contacts

import java.time.LocalDateTime



class PhoneBook(private val wrappers: MutableList<Wrapper> = mutableListOf()) {


    fun action() {
        while (true) {
            when (ask("[menu] Enter action (add, list, search, count, exit): ")) {
                "add" -> addWrapper()
                "list" -> listScreen()
                "search" -> search()
                "count" -> count()
                "exit" -> break
            }
        }
    }




    private fun count() {
        println("The Phone Book has ${wrappers.size} records.\n")
    }

    private fun newPerson() {
        val messages = listOf("Enter the name: ",
            "Enter the surname: ",
            "Enter the birth date: ",
            "Enter the gender (M, F): ",
            "Enter the number: ")
        val errorMessages = listOf("Bad name!", "Bad surname!", "Bad birth date!", "Bad gender!", "Wrong number format!")
        val (name, surname, birthDate, gender, number) = multipleAsk(messages = messages, errorMessages =  errorMessages)
        wrappers.add(Person(
            name = name, surname =  surname, birthDate = birthDate, number = number,
            gender =  gender, timeCreated = LocalDateTime.now().toString()))
            .also { println("The record added.\n") }
    }

    private fun addWrapper() {
        when (ask(Constants.type)) {
            "person" -> newPerson()
            "organization" -> newOrganization()
            else -> println("Wrong type")
        }
    }


    private fun delete(index: Int) {
        wrappers.removeAt(index)
    }

    private fun newOrganization() {
        val messages = listOf(
            "Enter the organization name: ",
            "Enter the address: ",
            "Enter the number:"
        )
        val errorMessages = listOf("", "", "")
        val (name, address, number) = multipleAsk(messages = messages, errorMessages = errorMessages)
        wrappers.add(Organization(name, address, phoneNumberChecker(number),
            timeCreated = LocalDateTime.now().toString()))
            .also { println("The record added.\n") }
    }

    private fun search() {
        while (true) {
            val query = ask(Constants.searchQuery)
            val results = searchRecords(wrappers, query)
            if (results.isEmpty()) {
                println("No results found")
            } else {
                println("Found ${results.size} results:")
                results.listByIndexes()
                println()
            }
            when(val command = ask(Constants.searchCycle)) {
                "back" -> break
                "again" -> continue
                else ->  {
                    recordScreen(command.toInt() - 1)
                    break
                }
            }
        }
    }

    private fun recordScreen(recordIndex: Int) {
        val rec = wrappers[recordIndex]
        rec.infoForm()
        while (true) {
            when (ask(Constants.recordCycle)) {
                "edit" -> rec.edit()
                "delete" -> delete(index = recordIndex)
                "menu" ->  {
                    println()
                    break
                }
            }
        }
    }

    private fun listScreen() {
        wrappers.list()
        while (true) {
            when(val command = ask(Constants.listCycle)) {
                "back" -> break
                else -> {
                    recordScreen(command.toInt() - 1)
                    println()
                    break
                }
            }
        }
    }

    private fun MutableSet<Int>.listByIndexes() {
        for (i in this) {
            println(wrappers[i])
        }
    }

    private fun List<Wrapper>.list() {
        for ((i,e) in this.withIndex()) {
            println("${i + 1}. $e")
        }
    }

}

