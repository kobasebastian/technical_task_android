package pl.kobasebastian.slidepracticaltest.data.model

data class AddUserRequest(
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)