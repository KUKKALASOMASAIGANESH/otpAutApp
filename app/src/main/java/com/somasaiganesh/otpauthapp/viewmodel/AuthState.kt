package com.somasaiganesh.otpauthapp.viewmodel

sealed class AuthState {
    object EmailInput : AuthState()
    object OtpInput : AuthState()
    data class LoggedIn(val startTime: Long) : AuthState()
    data class Error(val message: String) : AuthState()
}

