package hu.bme.aut.android.together.domain.model

import java.util.*

class DomainAddableEvent(
    var title: String,
    var isPrivate: Boolean,
    var isMaximumParticipantCountRuleSet: Boolean,
    var maximumParticipantCount: Int,
    var isJoinRequestAutoAcceptAllowed: Boolean,
    var category: String,
    var startDate: Date,
    var endDate: Date,
    var location: String,
    var description: String
)