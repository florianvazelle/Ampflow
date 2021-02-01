package com.florianvazelle.ampflow

import android.content.Context
import androidx.preference.PreferenceManager


class UserPreferences(private val context: Context) {

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    val enabled by lazy { prefs.getBoolean("enabled", true) }
    val onLockScreen by lazy { prefs.getBoolean("lock_screen", true) }
    val onLowPower by lazy { prefs.getBoolean("low_power", false) }
}