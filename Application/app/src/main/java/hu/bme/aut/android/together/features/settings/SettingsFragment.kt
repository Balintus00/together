package hu.bme.aut.android.together.features.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hu.bme.aut.android.together.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.profile_settings_preference, rootKey)
    }
}