package hu.bme.aut.android.together.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EventPublicRuleOptions(
    var isParticipantCountLimited: Boolean,
    var maximumParticipantCount: Int,
    var isJoinAutoAcceptEnabled: Boolean
) : Parcelable