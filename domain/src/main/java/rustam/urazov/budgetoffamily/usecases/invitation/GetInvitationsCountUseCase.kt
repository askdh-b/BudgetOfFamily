package rustam.urazov.budgetoffamily.usecases.invitation

import rustam.urazov.budgetoffamily.models.InvitationData

class GetInvitationsCountUseCase {

    fun execute(invitations: List<InvitationData>) = invitations.size
}