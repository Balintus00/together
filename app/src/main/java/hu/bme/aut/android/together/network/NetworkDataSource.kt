package hu.bme.aut.android.together.network

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.model.network.NetworkEventMessage

interface NetworkDataSource {

    fun getUserProfileById(id: Long): DomainProfileData?

    fun getIncomingInvitesById(id: Long): List<NetworkEventMessage>
}