package hu.bme.aut.android.together.domain.model

import java.util.*

data class DomainUserProfile(
    val profileId: Long,
    val name: String,
    val username: String,
    val dateOfBirth: Date,
    val profileImageUri: String,
    val incomingInvitesCount: Int
)