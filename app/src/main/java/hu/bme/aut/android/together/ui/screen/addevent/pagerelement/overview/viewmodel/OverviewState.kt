package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.viewmodel

import hu.bme.aut.android.together.ui.model.UploadResponse

sealed class OverviewState

object ReviewState: OverviewState()

object Loading: OverviewState()

data class EventUploaded(val response: UploadResponse): OverviewState()
