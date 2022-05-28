package rustam.urazov.budgetoffamily.usecases.invitation

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Invitation
import rustam.urazov.budgetoffamily.repositories.InvitationRepository

class SendInvitationUseCase(private val invitationRepository: InvitationRepository) {

    suspend fun execute(accessToken: AccessToken, invitation: Invitation) =
        invitationRepository.sendInvitation(accessToken, invitation)
}