package contacts

import kotlinx.serialization.Serializable

@Serializable
sealed interface Wrapper {
    val timeCreated: String
    val timeLastEdited: String
    fun edit()

    fun searchForm(): List<String>

    fun infoForm()
}

