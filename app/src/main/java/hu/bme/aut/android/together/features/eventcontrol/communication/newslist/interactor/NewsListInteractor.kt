package hu.bme.aut.android.together.features.eventcontrol.communication.newslist.interactor

import hu.bme.aut.android.together.model.domain.DomainEventNews
import hu.bme.aut.android.together.network.NetworkDataSource
import hu.bme.aut.android.together.persistence.repository.EventNewsRepository
import javax.inject.Inject

class NewsListInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val repository: EventNewsRepository
) {

    fun loadEventNews(eventId: Long) : List<DomainEventNews> {
        return networkDataSource.getEventNewsById(eventId)?.let {
            repository.persistEventNews(eventId, it)
            it
        } ?: repository.loadPersistedEventNewsByEventId(eventId)
    }

}