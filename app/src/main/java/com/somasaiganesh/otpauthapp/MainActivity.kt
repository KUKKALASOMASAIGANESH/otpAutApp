package com.somasaiganesh.otpauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.somasaiganesh.otpauthapp.ui.theme.OtpAuthAppTheme
import com.somasaiganesh.otpauthapp.viewmodel.AuthState
import com.somasaiganesh.otpauthapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OtpAuthAppTheme {
                Surface(modifier = Modifier) {
                    AuthFlow()
                }
            }
        }
    }

    @Composable
    private fun AuthFlow() {
        val viewModel: AuthViewModel = viewModel()

        when (val state = viewModel.authState.value) {

            is AuthState.EmailInput -> {
                LoginScreen(
                    onSendOtp = { email ->
                        viewModel.sendOtp(email)
                    }
                )
            }

            is AuthState.OtpInput -> {
                OtpScreen(
                    onVerifyOtp = { otp ->
                        viewModel.verifyOtp(otp)
                    }
                )
            }

            is AuthState.LoggedIn -> {
                SessionScreen(
                    startTime = state.startTime,
                    onLogout = {
                        viewModel.logout()
                    }
                )
            }

            is AuthState.Error -> {
                LoginScreen(
                    onSendOtp = { email ->
                        viewModel.sendOtp(email)
                    }
                )
            }
        }
    }
}
