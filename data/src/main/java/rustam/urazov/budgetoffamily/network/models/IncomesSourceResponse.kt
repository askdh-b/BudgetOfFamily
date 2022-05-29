package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class IncomesSourceResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sum") val sum: Float,
    @SerializedName("monthDay") val monthDay: Int
)