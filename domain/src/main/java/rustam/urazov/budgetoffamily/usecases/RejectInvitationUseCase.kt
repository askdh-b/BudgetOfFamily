package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.InvitationRepository

class RejectInvitationUseCase(private val invitationRepository: InvitationRepository) {

    suspend fun execute(accessToken: AccessToken, id: Int) =
        invitationRepository.rejectInvitation(accessToken, id)
}