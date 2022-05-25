package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.InvitationData

interface InvitationRepository {

    suspend fun getInvitations(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToInvitation(invitations: List<*>): List<InvitationData>
}