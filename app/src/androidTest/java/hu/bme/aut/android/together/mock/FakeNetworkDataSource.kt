package hu.bme.aut.android.together.mock

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.model.network.NetworkEventMessage
import hu.bme.aut.android.together.network.NetworkDataSource
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor() : NetworkDataSource {
    override fun getUserProfileById(id: Long): DomainProfileData {
        return DomainProfileData(
            1,
            "Botond",
            "B0T0ND",
            SimpleDateFormat("yyyy.MM.d.", Locale.ENGLISH).run { parse("1999.09.01.") }!!,
            "https://picsum.photos/200",
            1
        )
    }

    override fun getIncomingInvitesById(id: Long): List<NetworkEventMessage> {
        return listOf(
            NetworkEventMessage(
                1,
                "Come join my birthday party!",
                "KR1ST0F",
                "Lorem ipsum dolor sit amet!"
            )
        )
    }
}