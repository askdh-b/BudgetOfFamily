package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class GoalResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("incomePercentile") val incomePercentile: Float,
    @SerializedName("actualSum") val actualSum: Float,
    @SerializedName("necessarySum") val necessarySum: Float
)