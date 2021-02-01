package com.florianvazelle.ampflow

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    companion object {
        fun newInstance(rootKey: String?): SettingsFragment {
            val fragment = SettingsFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PREFERENCE_ROOT, rootKey)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String){
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}