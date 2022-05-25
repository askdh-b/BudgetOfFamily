package rustam.urazov.budgetoffamily.models

data class InvitationData(
    val senderId: Int,
    val recipientId: Int,
    val familyId: Int
)