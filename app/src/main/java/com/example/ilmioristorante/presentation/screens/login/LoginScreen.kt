package com.example.ilmioristorante.presentation.screens.login

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
import com.example.ilmioristorante.util.Screen
import com.example.ilmioristorante.presentation.screens.common.ButtonComponent
import com.example.ilmioristorante.presentation.screens.common.ClickableLoginTextComponent
import com.example.ilmioristorante.presentation.screens.common.DividerTextComponent
import com.example.ilmioristorante.presentation.screens.common.HeadingTextComponent
import com.example.ilmioristorante.presentation.screens.common.MyTextFieldComponent
import com.example.ilmioristorante.presentation.screens.common.NormalTextComponent
import com.example.ilmioristorante.presentation.screens.common.PasswordTextFieldComponent
import com.example.ilmioristorante.presentation.screens.common.UnderLinedTextComponent
import com.example.ilmioristorante.presentation.viewmodels.LoginViewModel

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val userDetails = loginViewModel.isUserLoginSuccess
    LaunchedEffect(userDetails.value) {
        if(userDetails.value != null){
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = "Login")
                HeadingTextComponent(value = "Welcome Back")
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = "Email",
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = "Forgot your password ?")

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Login",
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate(Screen.Signup.route)
                })
            }
        }

        // future scope to add on..
//        if(loginViewModel.loginInProgress.value) {
//            CircularProgressIndicator()
//        }
    }
}

@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun LoginScreenPreview() {
    val context = LocalContext.current
    LoginScreen(NavHostController(context))
}