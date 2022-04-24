package rustam.urazov.domain.repositories

import rustam.urazov.domain.ResultWrapper
import rustam.urazov.domain.models.UserAuthData

interface UserAuthorizationRepository {
    suspend fun authorize(userAuthData: UserAuthData): ResultWrapper<Any>
}