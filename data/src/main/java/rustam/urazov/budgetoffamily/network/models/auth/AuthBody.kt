package rustam.urazov.budgetoffamily.network.models.auth

import com.google.gson.annotations.SerializedName

data class AuthBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)