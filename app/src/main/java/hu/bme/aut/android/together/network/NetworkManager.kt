package hu.bme.aut.android.together.network

import hu.bme.aut.android.together.model.domain.DomainProfileData
import hu.bme.aut.android.together.model.network.NetworkEventMessage
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NetworkManager @Inject constructor() : NetworkDataSource {

    companion object {
        private const val SIMULATED_LOADING_TIME_MS = 500L
    }

    //TODO: RedundantNullabeReturnType warning is suppressed, because it will be fixed
    // in the actual implementation later

    @Suppress("RedundantNullableReturnType")
    override fun getUserProfileById(@Suppress("UNUSED_PARAMETER") id: Long): DomainProfileData? {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
        return DomainProfileData(
            1,
            "Botond",
            "B0T0ND",
            SimpleDateFormat("yyyy.MM.d.", Locale.ENGLISH).run { parse("1999.09.01.") }!!,
            "https://picsum.photos/200",
            1
        )
    }

    @Suppress("RedundantNullableReturnType")
    override fun getIncomingInvitesById(@Suppress("UNUSED_PARAMETER") id: Long): List<NetworkEventMessage> {
        Thread.sleep(SIMULATED_LOADING_TIME_MS)
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