package rustam.urazov.budgetoffamily.usecases.invitation

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.InvitationRepository

class GetInvitationsUseCase(private val invitationRepository: InvitationRepository) {

    suspend fun execute(accessToken: AccessToken) = invitationRepository.getInvitations(accessToken)
}