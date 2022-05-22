package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.RefreshToken
import rustam.urazov.budgetoffamily.models.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getAccessToken(): AccessToken

    fun getRefreshToken(): RefreshToken
}