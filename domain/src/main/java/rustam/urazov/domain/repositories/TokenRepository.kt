package rustam.urazov.domain.repositories

import rustam.urazov.domain.models.Token

interface TokenRepository {

    fun saveToken(token: Token)

    fun getToken(): Token
}