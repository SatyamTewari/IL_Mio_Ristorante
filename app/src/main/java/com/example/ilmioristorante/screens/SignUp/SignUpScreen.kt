package com.example.ilmioristorante.screens.SignUp

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ilmioristorante.R
import com.example.ilmioristorante.screens.common.*
import com.example.ilmioristorante.navigation.Screen

@ExperimentalMaterial3Api
@Composable
fun SignUpScreen() {

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
            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = "Hey there, ")
                HeadingTextComponent(value = "Create an Account ")
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = "First Name",
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
//                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = "Last Name",
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
//                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = "Email",
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
//                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
//                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Register",
                    onButtonClicked = {
//                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
//                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
//                    PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
                })
            }

        }

//        if(signupViewModel.signUpInProgress.value) {
//            CircularProgressIndicator()
//        }
    }


}

@ExperimentalMaterial3Api
@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}