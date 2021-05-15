package hu.bme.aut.android.together.model.presentation

data class ProfileData(
    val name: String,
    val username: String,
    val dateOfBirth: String,
    val profileImageUri: String,
    val incomingInvitesCount: Int
)