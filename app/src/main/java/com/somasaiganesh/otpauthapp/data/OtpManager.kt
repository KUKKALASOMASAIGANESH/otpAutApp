package com.somasaiganesh.otpauthapp.data

import android.util.Log
import kotlin.random.Random

data class OtpData(
    val otp: String,
    val createdAt: Long,
    var attempts: Int
)

class OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()

    fun generateOtp(email: String): String {
        val otp = Random.nextInt(100000, 999999).toString()

        otpStore[email] = OtpData(
            otp = otp,
            createdAt = System.currentTimeMillis(),
            attempts = 0
        )

        // ⭐ THIS LINE WAS MISSING
        Log.d("OTP_TEST", "OTP generated for $email : $otp")

        return otp
    }

    fun validateOtp(email: String, inputOtp: String): Boolean {
        val data = otpStore[email] ?: return false

        // expiry check (60 seconds)
        if (System.currentTimeMillis() - data.createdAt > 60_000) {
            return false
        }

        // attempts check
        if (data.attempts >= 3) {
            return false
        }

        data.attempts++

        return data.otp == inputOtp
    }
}
