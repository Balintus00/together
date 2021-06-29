package hu.bme.aut.android.together.ui.model

class EventDetails(
    val eventId: Long,
    var title: String,
    val imageUrl: String,
    var category: String,
    var startDate: String,
    var endDate: String,
    var location: String,
    var description: String,
    val isParticipantCountLimited: Boolean,
    val maxParticipantCount: Int,
    val currentParticipantCount: Int,
    val isPrivate: Boolean,
    val isParticipant: Boolean,
    val isOrganiser: Boolean
)