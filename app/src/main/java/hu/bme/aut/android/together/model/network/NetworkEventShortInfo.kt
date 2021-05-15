package hu.bme.aut.android.together.model.network

class NetworkEventShortInfo(
    val eventId: Long,
    val name: String,
    val location: String,
    val startDate: String,
    val startTime: String,
    val endDate: String,
    val endTime: String,
    val imageUrl: String,
    val isComing: Boolean
)