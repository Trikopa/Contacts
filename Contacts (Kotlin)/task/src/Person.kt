package contacts

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
class Person(var name: String,
             var surname: String,
             var number: String,
             var birthDate: String,
             var gender: String,
             override val timeCreated: String,
             override var timeLastEdited: String = LocalDateTime.now().toString(),

): Wrapper {
    override fun toString(): String {
        return "$name $surname"
    }


    override fun edit() {
        when(ask(Constants.personFields)) {
            "name" -> {
                this.name = ask(Constants.name)
                this.timeLastEdited = LocalDateTime.now().toString()
                println("Saved")
                infoForm()
            }

            "surname" ->  {
                this.surname = ask(Constants.surname)
                this.timeLastEdited = LocalDateTime.now().toString()
                println("Saved")
                infoForm()
            }
            "number" ->  {
                this.number = phoneNumberChecker( ask(Constants.number))
                this.timeLastEdited = LocalDateTime.now().toString()
                println("Saved")
                infoForm()

            }
            "birth" -> {
                this.birthDate = ask(Constants.birthDate)
                this.timeLastEdited = LocalDateTime.now().toString()
                println("Saved")
                infoForm()
            }
            "gender" ->  {
                this.gender = ask(Constants.gender)
                this.timeLastEdited = LocalDateTime.now().toString()
                println("Saved")
                infoForm()
            }
            else -> println("Invalid")
        }
    }

    override fun searchForm(): List<String> {
        return listOf(this.number, this.surname, this.number, this.gender, this.birthDate)
    }

    override fun infoForm() {
        println("""Name: $name
Surname: $surname
Birth date: $birthDate
Gender: $gender
Number: $number
Time created: $timeCreated
Time last edit: $timeLastEdited

""")
    }
}
