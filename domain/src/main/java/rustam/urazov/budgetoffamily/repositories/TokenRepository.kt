package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.models.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getToken(): Token
}