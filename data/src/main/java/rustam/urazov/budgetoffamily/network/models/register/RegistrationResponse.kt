package rustam.urazov.budgetoffamily.network.models.register

import com.google.gson.annotations.SerializedName
import rustam.urazov.budgetoffamily.network.models.auth.TokenNetwork

data class RegistrationResponse(
    @SerializedName("accessToken") val accessToken: TokenNetwork
)