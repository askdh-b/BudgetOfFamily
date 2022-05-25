package rustam.urazov.budgetoffamily.models

data class SpendingData(
    val userId: Int,
    val firstName: String,
    val sum: Float,
    val name: String,
    val creationDate: String
)
