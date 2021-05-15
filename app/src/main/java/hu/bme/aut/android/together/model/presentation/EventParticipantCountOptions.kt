package hu.bme.aut.android.together.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EventParticipantCountOptions(val isMaximumParticipantCountRuleSet: Boolean, val newMaxParticipantCount: Int): Parcelable