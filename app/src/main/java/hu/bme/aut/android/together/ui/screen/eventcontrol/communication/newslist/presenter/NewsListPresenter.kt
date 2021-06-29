package hu.bme.aut.android.together.ui.screen.eventcontrol.communication.newslist.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.domain.interactor.NewsListInteractor
import hu.bme.aut.android.together.ui.model.EventNews
import javax.inject.Inject

class NewsListPresenter @Inject constructor(
    private val interactor: NewsListInteractor
) {

    suspend fun loadEventNews(eventId: Long) = withIOContext {
        interactor.loadEventNews(eventId).map {
            EventNews(
                it.title, it.author, it.message
            )
        }
    }

}