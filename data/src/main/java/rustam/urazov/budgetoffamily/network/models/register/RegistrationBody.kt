package rustam.urazov.budgetoffamily.network.models.register

import com.google.gson.annotations.SerializedName

data class RegistrationBody(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)