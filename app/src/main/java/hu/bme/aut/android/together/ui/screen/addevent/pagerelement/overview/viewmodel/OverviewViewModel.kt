package hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.overview.presenter.OverviewPresenter
import hu.bme.aut.android.together.ui.model.AddableEvent
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val presenter: OverviewPresenter
) : RainbowCakeViewModel<OverviewState>(ReviewState) {

    fun uploadEvent(addableEvent: AddableEvent) = execute {
        viewState = Loading
        presenter.uploadEvent(addableEvent).let {
            viewState = EventUploaded(it)
        }
    }

}