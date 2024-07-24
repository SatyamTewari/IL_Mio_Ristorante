package com.example.ilmioristorante.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ilmioristorante.R
import com.example.ilmioristorante.screens.common.*

@ExperimentalMaterial3Api
@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = "Login")
                HeadingTextComponent(value = "Welcome Back")
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = "Email",
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
//                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
//                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
//                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
//                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = "Forgot your password ?")

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Login",
                    onButtonClicked = {
//                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
//                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

//                DividerTextComponent()

//                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
//                })
            }
        }

//        if(loginViewModel.loginInProgress.value) {
//            CircularProgressIndicator()
//        }
    }


//    SystemBackButtonHandler {
//        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
//    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}