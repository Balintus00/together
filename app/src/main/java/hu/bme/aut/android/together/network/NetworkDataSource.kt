package hu.bme.aut.android.together.network

import hu.bme.aut.android.together.model.domain.DomainEventInvitation
import hu.bme.aut.android.together.model.domain.DomainEventShortInfo
import hu.bme.aut.android.together.model.domain.DomainProfileData

interface NetworkDataSource {

    fun getUserProfileById(id: Long): DomainProfileData?

    fun getIncomingInvitesById(id: Long): List<DomainEventInvitation>?

    fun getComingEventShortInfoListByProfileId(profileId: Long) : List<DomainEventShortInfo>?

    fun getPastEventShortInfoListByProfileId(profileId: Long) : List<DomainEventShortInfo>?
}