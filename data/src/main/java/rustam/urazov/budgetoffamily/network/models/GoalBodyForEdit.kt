package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class GoalBodyForEdit(
    @SerializedName("name") val name: String,
    @SerializedName("incomesPercentile") val incomePercentile: Float,
    @SerializedName("progress") val progress: Float,
    @SerializedName("sum") val sum: Float
)