package com.example.googleauthapp.presentation.screen.login

import android.app.Activity
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.presentation.screen.common.StartActivityForResult
import com.example.googleauthapp.presentation.screen.common.signIn

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()

) {

    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState

    Scaffold(
        topBar = {
            LoginTopBar()
        },
        content = {
            LoginContent(signedInState = signedInState, messageBarState = messageBarState) {

                loginViewModel.saveSignedInState(signedIn = true)

            }
        }
    )

val activity = LocalContext.current as Activity
    StartActivityForResult(
        key = signedInState,
        onResultReceived = {
                           idToken ->
            Log.d("LoginScreen: ","Google Token  $idToken")
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(false)
        }

        ) {

        activityLauncher ->
        if (signedInState){
            signIn(activity,
            launchActivityResult = {
                intentSenderRequest ->
                activityLauncher.launch(intentSenderRequest)
            },
                accountNotFound = {
                    loginViewModel.saveSignedInState(signedIn = false)
                    loginViewModel.updateMessageBarState()
                }
                )
        }

    }

}