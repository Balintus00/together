package hu.bme.aut.android.together.ui.screen.currentevents.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.currentevents.presenter.PastEventListPresenter
import javax.inject.Inject

@HiltViewModel
class PastEventListViewModel @Inject constructor(
    private val pastEventListPresenter: PastEventListPresenter
) : RainbowCakeViewModel<EventListState>(Loading) {

    fun loadPastEventShortInfoListByProfileId(profileId: Long) = execute {
        viewState = Loading
        try {
            pastEventListPresenter.loadPastEventShortInfoByProfileId(profileId).let{
                viewState = EventListLoaded(it)
            }
        }
        catch (e: RuntimeException) {
            viewState = LoadingError(e.localizedMessage!!)
        }
    }

}