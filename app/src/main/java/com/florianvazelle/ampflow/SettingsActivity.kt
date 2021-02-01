package com.florianvazelle.ampflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen


class SettingsActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment.newInstance("preference_root"))
                    .commit()
        }
    }

    override fun onPreferenceStartScreen(caller: PreferenceFragmentCompat?, pref: PreferenceScreen): Boolean {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment.newInstance(pref.key))
                .addToBackStack(null)
                .commit()
        return true
    }
}