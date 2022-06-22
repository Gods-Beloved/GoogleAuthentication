package com.example.googleauthapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.googleauthapp.domain.repository.DataStoreOperations
import com.example.googleauthapp.util.Constant.PREFERENCES_SIGNED_IN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreOperationImpl @Inject constructor(
private val dataStore:DataStore<Preferences>
):DataStoreOperations{

    private object PreferencesKey{
        val signedKey = booleanPreferencesKey(name = PREFERENCES_SIGNED_IN_KEY)
    }

    override suspend fun saveSignedInState(signedIn: Boolean) {
dataStore.edit {
    preferences ->
    preferences[PreferencesKey.signedKey] = signedIn
}
    }


    override fun readSignedInState(): Flow<Boolean> {

        return dataStore.data.catch {
            exception -> if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
            .map {
                preferences ->
                val signedInState = preferences[PreferencesKey.signedKey] ?: false
                signedInState
            }
    }
}