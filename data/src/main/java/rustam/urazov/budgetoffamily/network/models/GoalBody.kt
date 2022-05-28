package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class GoalBody(
    @SerializedName("name") val name: String,
    @SerializedName("incomePercentile") val incomePercentile: Float,
    @SerializedName("sum") val sum: Float
)