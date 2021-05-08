package hu.bme.aut.android.together.model.presentation

class AddableEvent(
    var title: String,
    var isPrivate: Boolean,
    var isMaximumParticipantCountRuleSet: Boolean,
    var maximumParticipantCount: Int,
    var isJoinRequestAutoAcceptAllowed: Boolean,
    var category: String,
    var startDate: String,
    var startTime: String,
    var endDate: String,
    var endTime: String,
    var location: String,
    var description: String
)