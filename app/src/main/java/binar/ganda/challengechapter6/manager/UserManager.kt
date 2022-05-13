package binar.ganda.challengechapter6.manager

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context : Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "USER_PREF")

    companion object {

        val ADDRESS = preferencesKey<String>("USER_ADDRESS")
        val COMPLETENAME = preferencesKey<String>("USER_COMPLETE_NAME")
        val CREATEAT = preferencesKey<String>("USER_CREATEAT")
        val DATEOFBIRTH = preferencesKey<String>("USER_DATEOFBIRTH")
        val EMAIL = preferencesKey<String>("USER_EMAIL")
        val ID = preferencesKey<String>("USER_ID")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val USERNAME = preferencesKey<String>("USER_USERNAME")
    }

    suspend fun saveData(
        address: String,
        completeName : String,
        createAt : String,
        dateOfBirth : String,
        email: String,
        id: String,
        password : String,
        username : String
    ){
        dataStore.edit {
            it[ADDRESS] = address
            it[COMPLETENAME] = completeName
            it[CREATEAT] = createAt
            it[DATEOFBIRTH] = dateOfBirth
            it[EMAIL] = email
            it[ID] = id
            it[PASSWORD] = password
            it[USERNAME] = username
        }
    }



    val userAddress : Flow<String> = dataStore.data.map {
        it[ADDRESS] ?: ""
    }
    val userCompleteName : Flow<String> = dataStore.data.map {
        it[COMPLETENAME] ?: ""
    }
    val userCreateAt : Flow<String> = dataStore.data.map {
        it[CREATEAT] ?: ""
    }
    val userDateOfBirth : Flow<String> = dataStore.data.map {
        it[COMPLETENAME] ?: ""
    }

    val userEmail : Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val userID : Flow<String> = dataStore.data.map {
        it[ID] ?: ""
    }

    val userPassword : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    val userUsername : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }
}