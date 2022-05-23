package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class SpendingBody(
    @SerializedName("sum") val sum: Float,
    @SerializedName("name") val name: String
)