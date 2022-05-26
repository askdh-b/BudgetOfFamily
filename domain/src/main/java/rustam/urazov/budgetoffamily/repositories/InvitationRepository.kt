package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.InvitationData

interface InvitationRepository {

    suspend fun getInvitations(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToInvitation(invitations: List<*>): List<InvitationData>

    suspend fun acceptInvitation(accessToken: AccessToken, id: Int): ResultWrapper<Any>

    suspend fun rejectInvitation(accessToken: AccessToken, id: Int): ResultWrapper<Any>
}