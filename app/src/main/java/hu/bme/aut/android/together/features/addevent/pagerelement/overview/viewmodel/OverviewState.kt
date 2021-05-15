package hu.bme.aut.android.together.features.addevent.pagerelement.overview.viewmodel

import hu.bme.aut.android.together.model.presentation.UploadResponse

sealed class OverviewState

object ReviewState: OverviewState()

object Loading: OverviewState()

data class EventUploaded(val response: UploadResponse): OverviewState()
