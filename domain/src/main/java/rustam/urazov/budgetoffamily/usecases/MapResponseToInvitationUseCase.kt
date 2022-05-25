package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.repositories.InvitationRepository

class MapResponseToInvitationUseCase(private val invitationRepository: InvitationRepository) {

    suspend fun execute(invitations: List<*>) = invitationRepository.mapToInvitation(invitations)
}