package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.UserData

interface UserRepository {

    suspend fun search(accessToken: AccessToken, q: String): ResultWrapper<Any>

    suspend fun leave(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToUser(users: List<*>): List<UserData>
}