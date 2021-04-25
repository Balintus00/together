package hu.bme.aut.android.together.network

import hu.bme.aut.android.together.model.domain.*

interface NetworkDataSource {

    fun getUserProfileById(id: Long): DomainProfileData?

    fun getIncomingInvitesById(id: Long): List<DomainEventInvitation>?

    fun getComingEventShortInfoListByProfileId(profileId: Long) : List<DomainEventShortInfo>?

    fun getPastEventShortInfoListByProfileId(profileId: Long) : List<DomainEventShortInfo>?

    fun searchEventsByQueryParameter(queryParameter: DomainEventQueryParameter) : List<DomainEventShortInfo>?

    fun getEventDetailsById(eventId: Long) : DomainEventDetails?

    fun getEventDescriptionDataById(eventId: Long) : DomainEventDescriptionData?

    fun getCommunicationPagerDataById(eventId: Long) : DomainCommunicationPagerData?

    fun getEventNewsById(eventId: Long) : List<DomainEventNews>?

    fun getEventQuestionsAndAnswersByEventId(eventId: Long): List<DomainEventQuestionAndAnswer>?
}