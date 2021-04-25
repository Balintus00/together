package hu.bme.aut.android.together.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EventQueryParameter(
    val name: String,
    val place: String,
    val radius: Int,
    val type: String,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
) : Parcelable