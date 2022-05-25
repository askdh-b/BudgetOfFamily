package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class SpendingResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("sum") val sum: Float,
    @SerializedName("name") val name: String,
    @SerializedName("creationDate") val creationDate: String
)