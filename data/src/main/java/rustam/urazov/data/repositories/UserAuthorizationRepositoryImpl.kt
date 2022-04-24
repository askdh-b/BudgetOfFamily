package rustam.urazov.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.data.network.Service
import rustam.urazov.data.network.models.auth.AuthBody
import rustam.urazov.data.network.safeCall
import rustam.urazov.domain.ResultWrapper
import rustam.urazov.domain.models.UserAuthData
import rustam.urazov.domain.repositories.UserAuthorizationRepository

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