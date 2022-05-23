package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class IncomeBody(
    @SerializedName("sum") val sum: Float,
    @SerializedName("name") val name: String
)
