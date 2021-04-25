package hu.bme.aut.android.together.features.eventcontrol.communication.newslist.presenter

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.android.together.features.eventcontrol.communication.newslist.interactor.NewsListInteractor
import hu.bme.aut.android.together.model.presentation.EventNews
import javax.inject.Inject

class NewsListPresenter @Inject constructor(
    val interactor: NewsListInteractor
) {

    suspend fun loadEventNews(eventId: Long) = withIOContext {
        interactor.loadEventNews(eventId).map {
            EventNews(
                it.title, it.author, it.message
            )
        }
    }

}