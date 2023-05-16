package contacts

import java.time.LocalDateTime

data class Person(val name: String,
                  val surname: String,
                  val number: String,
                  val birthDate: String,
                  val gender: String,
                  override val isPerson: Boolean = true,
                  override val timeCreated: String,
                  override val timeLastEdited: String = LocalDateTime.now().toString()
): Wrapper() {
    override fun toString(): String {
        return "$name $surname"
    }
    override fun infoForm() {
        println("""Name: $name
Surname: $surname
Birth date: $birthDate
Gender: $gender
Number: $number
Time created: $timeCreated
Time last edit: $timeLastEdited""")
    }
}
