package hu.bme.aut.android.together.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EventParticipantCountOptions(val isMaximumParticipantCountRuleSet: Boolean, val newMaxParticipantCount: Int): Parcelable