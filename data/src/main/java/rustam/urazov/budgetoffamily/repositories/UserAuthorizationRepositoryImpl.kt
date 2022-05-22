package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.AuthBody
import rustam.urazov.budgetoffamily.network.safeCall
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.UserAuthData
import rustam.urazov.budgetoffamily.repositories.UserAuthorizationRepository

class UserAuthorizationRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : UserAuthorizationRepository {

    override suspend fun authorize(userAuthData: UserAuthData): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.auth(
                AuthBody(
                    username = userAuthData.username,
                    password = userAuthData.password
                )
            )
        })
}