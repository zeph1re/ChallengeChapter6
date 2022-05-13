package binar.ganda.challengechapter6.model

data class LoginResponse(
    val responseuser : ResponseUser,
    val message: String,
    val code: Int
)
