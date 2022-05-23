package rustam.urazov.budgetoffamily.models

data class GoalData(
    val userId: Int,
    val name: String,
    val incomePercentile: Float,
    val actualSum: Float,
    val necessarySum: Float
)