package rustam.urazov.budgetoffamily.models

data class GoalForEdit(
    val name: String,
    val incomePercentile: Float,
    val progress: Float,
    val sum: Float
)