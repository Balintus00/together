package hu.bme.aut.android.together.domain.interactor

import hu.bme.aut.android.together.domain.model.DomainEventNews
import hu.bme.aut.android.together.data.network.NetworkDataSource
import hu.bme.aut.android.together.data.disk.repository.EventNewsRepository
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