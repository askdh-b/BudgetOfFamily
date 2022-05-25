package rustam.urazov.budgetoffamily.models

data class Token(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String
)