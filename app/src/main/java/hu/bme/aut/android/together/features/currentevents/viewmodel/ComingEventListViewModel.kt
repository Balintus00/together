package hu.bme.aut.android.together.features.currentevents.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.currentevents.presenter.ComingEventListPresenter
import javax.inject.Inject

@HiltViewModel
class ComingEventListViewModel @Inject constructor(
    private val comingEventListPresenter: ComingEventListPresenter
) : RainbowCakeViewModel<EventListState>(Loading) {

    fun loadComingEventShortInfoListByProfileId(profileId: Long) = execute {
        viewState = Loading
        comingEventListPresenter.loadPastEventShortInfoByProfileId(profileId).let{
            viewState = EventListLoaded(it)
        }
    }
}