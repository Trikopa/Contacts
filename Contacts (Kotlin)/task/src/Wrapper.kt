package contacts

import java.time.LocalDateTime

abstract class Wrapper {
    abstract val isPerson: Boolean
    abstract val timeCreated: String
    abstract val timeLastEdited: String


    abstract fun infoForm()
}

