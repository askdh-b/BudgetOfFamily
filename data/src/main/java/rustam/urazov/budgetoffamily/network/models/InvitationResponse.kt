package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class InvitationResponse(
    @SerializedName("senderId") val senderId: Int,
    @SerializedName("recipientId") val recipientId: Int,
    @SerializedName("familyId") val familyId: Int
)