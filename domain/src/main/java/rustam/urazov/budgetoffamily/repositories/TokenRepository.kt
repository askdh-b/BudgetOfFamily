package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.RefreshToken
import rustam.urazov.budgetoffamily.models.Token

interface TokenRepository {

    suspend fun saveToken(token: Token)

    suspend fun getAccessToken(): AccessToken

    suspend fun getRefreshToken(): RefreshToken

    suspend fun getUserId(): Int
}