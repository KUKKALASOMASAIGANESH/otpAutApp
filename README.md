# OtpAuthApp

A passwordless authentication Android application built using **Email + OTP**, developed as part of an Android assignment.  
The app demonstrates Jetpack Compose, ViewModel-based architecture, local OTP handling, and session tracking.

---

## 🚀 Features

- Email-based passwordless login
- 6-digit OTP generated locally
- OTP expiry after 60 seconds
- Maximum 3 OTP validation attempts
- Resend OTP resets attempts and expiry
- Session screen with live duration timer (mm:ss)
- Logout functionality
- OTP and events logged using Logcat / Timber

---

## 🛠 Tech Stack

- **Language**: Kotlin  
- **UI**: Jetpack Compose  
- **Architecture**: ViewModel + UI State (one-way data flow)  
- **Async**: Kotlin Coroutines  
- **Logging SDK**: Timber  
- **Build System**: Gradle (Kotlin DSL)

---

## 🔐 OTP Logic & Expiry Handling

- OTP is generated as a random 6-digit number.
- OTP is stored locally in memory (no backend) using a `Map<Email, OtpData>`.
- Each OTP:
  - Expires after **60 seconds**
  - Allows a maximum of **3 attempts**
- Generating a new OTP:
  - Invalidates the old OTP
  - Resets attempt count
- OTP validation checks:
  - Expiry
  - Attempt count
  - OTP correctness

Since no backend was required, OTPs are logged to **Logcat** for testing and demonstration.

---

## 🧠 Data Structures Used

```kotlin
Map<String, OtpData>
