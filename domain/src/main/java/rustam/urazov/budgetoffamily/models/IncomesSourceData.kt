package rustam.urazov.budgetoffamily.models

data class IncomesSourceData(
    val userId: Int,
    val name: String,
    val sum: Float,
    val monthDay: Int
)