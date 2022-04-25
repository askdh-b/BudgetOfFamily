package rustam.urazov.budgetoffamily.models

data class NewUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)