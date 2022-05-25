package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)