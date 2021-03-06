package rustam.urazov.budgetoffamily.models

data class SpendingsSourceData(
    val id: Int,
    val userId: Int,
    val name: String,
    val sum: Float,
    val monthDay: Int
)