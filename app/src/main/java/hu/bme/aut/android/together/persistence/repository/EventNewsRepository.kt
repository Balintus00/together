package hu.bme.aut.android.together.persistence.repository

import hu.bme.aut.android.together.model.domain.DomainEventNews
import hu.bme.aut.android.together.model.persistence.PersistedEventNews
import hu.bme.aut.android.together.persistence.dao.EventNewsDao
import javax.inject.Inject

class EventNewsRepository @Inject constructor(
    private val dao: EventNewsDao
) {

    fun loadPersistedEventNewsByEventId(eventId: Long): List<DomainEventNews> {
        return dao.getCachedNewsByEventId(eventId).map {
            DomainEventNews(it.id, it.title, it.author, it.message)
        }
    }

    fun persistEventNews(eventId: Long, eventNews: List<DomainEventNews>) {
        dao.persistEventNews(*eventNews.map {
            PersistedEventNews(it.id, it.title, it.author, it.message, eventId)
        }.toTypedArray())
    }

}