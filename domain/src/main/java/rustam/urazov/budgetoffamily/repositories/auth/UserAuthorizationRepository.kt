package rustam.urazov.budgetoffamily.repositories.auth

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.UserAuthData

interface UserAuthorizationRepository {

    suspend fun authorize(userAuthData: UserAuthData): ResultWrapper<Any>
}