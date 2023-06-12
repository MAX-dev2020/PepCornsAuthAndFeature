package com.example.pepcorns

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApplication : Application() {

    companion object {
        private const val PREFS_NAME = "MyAppPrefs"
        private const val TOKEN_KEY = "token"

        private lateinit var sharedPreferences: SharedPreferences

        private var token: String = ""

        fun saveToken() {
            this.token = "cWGxvE29yGqUyAkFWHgiWKjDPmmtKicVZ95u4MKm"
            sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
        }

        fun getToken(): String {
            return token.ifEmpty {
                sharedPreferences.getString(TOKEN_KEY, "") ?: ""
            }
        }
    }

    override fun onCreate() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        saveToken()
        super.onCreate()
    }
}