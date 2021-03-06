package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class IncomesSourceBody(
    @SerializedName("name") val name: String,
    @SerializedName("sum") val sum: Float,
    @SerializedName("monthDay") val monthDay: Int
)