package com.mitocode.ecoats.presentation.on_boarding

import android.content.Context

class SaveOnBoardingScreen (context: Context) {

    val SHAREDNAME = "shared_preferences"
    val KEYSHARED = "shared_on_boarding_screen"

    val preferences = context.getSharedPreferences(SHAREDNAME,0)

    fun saveOnBoardingScreen(flag : Boolean)
    {
        preferences.edit().putBoolean(KEYSHARED,flag).apply()
    }

    fun getOnBoardingScreen():Boolean
    {
        return preferences.getBoolean(KEYSHARED,false)
    }
}