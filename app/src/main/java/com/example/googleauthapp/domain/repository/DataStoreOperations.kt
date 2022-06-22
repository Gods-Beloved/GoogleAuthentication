package com.example.googleauthapp.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveSignedInState(signedIn: Boolean)

    fun readSignedInState(): Flow<Boolean>
}