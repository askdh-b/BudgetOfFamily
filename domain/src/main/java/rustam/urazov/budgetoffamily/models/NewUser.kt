package rustam.urazov.budgetoffamily.models

data class NewUser(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String,
    val passwordAgain: String
)