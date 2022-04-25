package rustam.urazov.budgetoffamily.repositories.auth

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.network.Service
import rustam.urazov.budgetoffamily.network.models.auth.AuthBody
import rustam.urazov.budgetoffamily.network.safeCall
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.UserAuthData
import rustam.urazov.budgetoffamily.repositories.auth.UserAuthorizationRepository

class UserAuthorizationRepositoryImpl(
    private val service: Service,
    private val dispatcher: CoroutineDispatcher
) : UserAuthorizationRepository {

    override suspend fun authorize(userAuthData: UserAuthData): ResultWrapper<Any> {

        return safeCall(dispatcher, call = {
            service.auth(AuthBody(userAuthData.email, userAuthData.password))
        })
    }
}