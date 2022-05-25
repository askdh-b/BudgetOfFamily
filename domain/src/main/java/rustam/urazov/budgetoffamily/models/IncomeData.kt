package rustam.urazov.budgetoffamily.models

data class IncomeData(
    val userId: Int,
    val firstName: String,
    val sum: Float,
    val name: String,
    val creationDate: String
)