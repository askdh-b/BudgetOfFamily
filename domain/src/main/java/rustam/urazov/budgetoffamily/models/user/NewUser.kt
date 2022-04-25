package rustam.urazov.budgetoffamily.models.user

data class NewUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)