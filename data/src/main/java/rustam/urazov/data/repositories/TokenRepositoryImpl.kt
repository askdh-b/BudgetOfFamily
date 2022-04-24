package rustam.urazov.data.repositories

import rustam.urazov.data.storage.TokenStorageService
import rustam.urazov.data.storage.models.TokenStorage
import rustam.urazov.domain.models.Token
import rustam.urazov.domain.repositories.TokenRepository

class TokenRepositoryImpl(private val tokenStorageService: TokenStorageService): TokenRepository {

    override fun saveToken(token: Token) {
        val accessToken = TokenStorage(token.accessToken)

        tokenStorageService.save(accessToken)
    }

    override fun getToken() = Token(tokenStorageService.get().token)
}