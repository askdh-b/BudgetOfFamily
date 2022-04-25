package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.UserAuthData

interface UserAuthorizationRepository {
    suspend fun authorize(userAuthData: UserAuthData): ResultWrapper<Any>
}