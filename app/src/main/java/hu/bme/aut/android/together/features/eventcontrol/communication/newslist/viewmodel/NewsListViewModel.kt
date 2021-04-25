package hu.bme.aut.android.together.features.eventcontrol.communication.newslist.viewmodel

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.presenter.NewsListPresenter
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    val presenter: NewsListPresenter
) : RainbowCakeViewModel<NewsListState>(Loading) {

    fun loadEventNews(eventId: Long) = execute {
        viewState = Loading
        presenter.loadEventNews(eventId).let {
            viewState = NewsListLoaded(it)
        }
    }

}