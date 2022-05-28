package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.UserData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.UserResponse
import rustam.urazov.budgetoffamily.network.safeCall

class UserRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun search(accessToken: AccessToken, q: String): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.search(accessToken.token, q)
        })

    override suspend fun mapToUser(users: List<*>): List<UserData> {
        val userData = mutableListOf<UserData>()
        for (u in users) {
            val user = u as UserResponse
            userData.add(
                UserData(
                    id = user.id,
                    firstName = user.firstName,
                    lastName = user.lastName
                )
            )
        }
        return userData
    }
}