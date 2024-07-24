package com.example.ilmioristorante.presentation.screens.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.ilmioristorante.R
import com.example.ilmioristorante.presentation.navigation.Screen
import com.example.ilmioristorante.presentation.screens.common.ButtonComponent
import com.example.ilmioristorante.presentation.screens.common.ClickableLoginTextComponent
import com.example.ilmioristorante.presentation.screens.common.DividerTextComponent
import com.example.ilmioristorante.presentation.screens.common.HeadingTextComponent
import com.example.ilmioristorante.presentation.screens.common.MyTextFieldComponent
import com.example.ilmioristorante.presentation.screens.common.NormalTextComponent
import com.example.ilmioristorante.presentation.screens.common.PasswordTextFieldComponent
import com.example.ilmioristorante.presentation.viewmodels.SignupViewModel

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(
    navController: NavHostController,
    signupViewModel: SignupViewModel = hiltViewModel())
{

    val signupStatus = signupViewModel.isUserSignupSuccess
    LaunchedEffect(signupStatus.value) {
        if(signupStatus.value > 0){
            navController.navigate(Screen.Home.route)
        }
    }

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
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = "Last Name",
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = "Email",
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Register",
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.popBackStack()
                })
            }

        }

        // future scope to add on..
//        if(signupViewModel.signUpInProgress.value) {
//            CircularProgressIndicator()
//        }
    }


}

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    val context = LocalContext.current
    SignUpScreen(NavHostController(context))
}