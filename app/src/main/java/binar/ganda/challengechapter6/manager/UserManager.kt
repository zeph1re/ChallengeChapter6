package binar.ganda.challengechapter6.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "USER_PREF")

class UserManager(private val context : Context) {

    companion object {
        val ADDRESS = stringPreferencesKey("USER_ADDRESS")
        val COMPLETENAME =  stringPreferencesKey("USER_COMPLETE_NAME")
        val CREATEAT =  stringPreferencesKey("USER_CREATEAT")
        val DATEOFBIRTH =  stringPreferencesKey("USER_DATEOFBIRTH")
        val EMAIL =  stringPreferencesKey("USER_EMAIL")
        val ID =  stringPreferencesKey("USER_ID")
        val PASSWORD =  stringPreferencesKey("USER_PASSWORD")
        val USERNAME = stringPreferencesKey("USER_USERNAME")
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
        context.dataStore.edit {
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


    val userEmail : Flow<String> = context.dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val userPassword : Flow<String> = context.dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    val userUsername : Flow<String> = context.dataStore.data.map {
        it[USERNAME] ?: ""
    }

    suspend fun clearData() {
        context.dataStore.edit {
            it.clear()
        }
    }
}