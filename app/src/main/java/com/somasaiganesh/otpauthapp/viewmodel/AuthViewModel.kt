package com.somasaiganesh.otpauthapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.somasaiganesh.otpauthapp.data.OtpManager

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()

    var authState = mutableStateOf<AuthState>(AuthState.EmailInput)
        private set

    private var currentEmail: String = ""

    fun sendOtp(email: String) {
        currentEmail = email
        otpManager.generateOtp(email)
        authState.value = AuthState.OtpInput
    }

    fun verifyOtp(otp: String) {
        val success = otpManager.validateOtp(currentEmail, otp)

        if (success) {
            authState.value = AuthState.LoggedIn(System.currentTimeMillis())
        } else {
            authState.value = AuthState.Error("Invalid or expired OTP")
        }
    }

    fun logout() {
        authState.value = AuthState.EmailInput
    }
}
