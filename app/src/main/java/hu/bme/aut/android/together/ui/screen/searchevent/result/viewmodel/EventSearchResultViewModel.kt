package hu.bme.aut.android.together.ui.screen.searchevent.result.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.ui.screen.searchevent.result.presenter.EventSearchResultPresenter
import hu.bme.aut.android.together.ui.model.EventQueryParameter
import javax.inject.Inject

@HiltViewModel
class EventSearchResultViewModel @Inject constructor(
    val presenter: EventSearchResultPresenter
) : RainbowCakeViewModel<EventSearchResultState>(Loading) {

    fun loadResults(queryParameter: EventQueryParameter) = execute {
        viewState = Loading
        presenter.loadSearchResults(queryParameter).let {
            viewState = ResultsLoaded(it)
        }
    }

}