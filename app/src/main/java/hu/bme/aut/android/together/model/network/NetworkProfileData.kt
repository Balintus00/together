package hu.bme.aut.android.together.model.network

import java.util.*

class NetworkProfileData(
val profileId: Long,
val name: String,
val username: String,
val dateOfBirth: String,
val profileImageUri: String,
val incomingInvitesCount: Int
)