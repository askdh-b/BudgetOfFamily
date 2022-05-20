package rustam.urazov.budgetoffamily.repositories.token

import rustam.urazov.budgetoffamily.models.token.AccessToken
import rustam.urazov.budgetoffamily.models.token.RefreshToken
import rustam.urazov.budgetoffamily.models.token.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getAccessToken(): AccessToken

    fun getRefreshToken(): RefreshToken
}