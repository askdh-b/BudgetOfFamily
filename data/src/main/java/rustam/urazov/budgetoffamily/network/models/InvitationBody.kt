package rustam.urazov.budgetoffamily.network.models

import com.google.gson.annotations.SerializedName

data class InvitationBody(
    @SerializedName("recipientId") val recipientId: Int
)