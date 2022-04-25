package rustam.urazov.budgetoffamily.repositories.token

import rustam.urazov.budgetoffamily.models.token.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getToken(): Token
}