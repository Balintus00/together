package hu.bme.aut.android.together.features.currentevents.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.currentevents.presenter.ComingEventListPresenter
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class ComingEventListViewModel @Inject constructor(
    private val comingEventListPresenter: ComingEventListPresenter
) : RainbowCakeViewModel<EventListState>(Loading) {

    fun loadComingEventShortInfoListByProfileId(profileId: Long) = execute {
        viewState = Loading
        try {
            comingEventListPresenter.loadPastEventShortInfoByProfileId(profileId).let{
                viewState = EventListLoaded(it)
            }
        } catch (e: RuntimeException) {
            viewState = LoadingError(e.localizedMessage!!)
        }

    }
}