package hu.bme.aut.android.together.features.eventcontrol.eventsettings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hu.bme.aut.android.together.R

class EventSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.event_settings_preference, rootKey)
    }
}