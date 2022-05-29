package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Invitation
import rustam.urazov.budgetoffamily.models.InvitationData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.InvitationBody
import rustam.urazov.budgetoffamily.network.models.InvitationResponse
import rustam.urazov.budgetoffamily.network.safeCall

class InvitationRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : InvitationRepository {

    override suspend fun getInvitations(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getInvitations(accessToken.token)
        })

    override suspend fun sendInvitation(
        accessToken: AccessToken,
        invitation: Invitation
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.sendInvitation(
            accessToken.token, InvitationBody(
                recipientId = invitation.recipientId
            )
        )
    })

    override suspend fun mapToInvitation(invitations: List<*>): List<InvitationData> {
        val invitationData = mutableListOf<InvitationData>()
        for (i in invitations) {
            val invs = i as InvitationResponse
            invitationData.add(
                InvitationData(
                    id = invs.id,
                    firstName = invs.firstName,
                    lastName = invs.lastName,
                    senderId = invs.senderId,
                    recipientId = invs.recipientId,
                    familyId = invs.familyId
                )
            )
        }
        return invitationData
    }

    override suspend fun acceptInvitation(accessToken: AccessToken, id: Int): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.acceptInvitation(accessToken.token, id)
        })

    override suspend fun rejectInvitation(accessToken: AccessToken, id: Int): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.rejectInvitation(accessToken.token, id)
        })
}