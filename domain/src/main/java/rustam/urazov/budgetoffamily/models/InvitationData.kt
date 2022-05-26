package rustam.urazov.budgetoffamily.models

data class InvitationData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val senderId: Int,
    val recipientId: Int,
    val familyId: Int
)