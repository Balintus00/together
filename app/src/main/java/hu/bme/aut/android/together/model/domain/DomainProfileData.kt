package hu.bme.aut.android.together.model.domain

import java.util.*

data class DomainProfileData(
    val profileId: Long,
    val name: String,
    val username: String,
    val dateOfBirth: Date,
    val profileImageUri: String,
    val incomingInvitesCount: Int
)