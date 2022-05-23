package rustam.urazov.budgetoffamily.models

data class SpendingData(
    val userId: Int,
    val sum: Float,
    val name: String,
    val creationDate: String
)
