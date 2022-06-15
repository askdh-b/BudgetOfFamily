package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class InvitationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("username") val username: String,
    @SerializedName("senderId") val senderId: Int,
    @SerializedName("recipientId") val recipientId: Int,
    @SerializedName("familyId") val familyId: Int
)