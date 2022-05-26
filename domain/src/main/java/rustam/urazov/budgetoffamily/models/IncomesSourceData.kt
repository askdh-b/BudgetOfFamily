package rustam.urazov.budgetoffamily.models

data class IncomesSourceData(
    val id: Int,
    val userId: Int,
    val name: String,
    val sum: Float,
    val monthDay: Int
)