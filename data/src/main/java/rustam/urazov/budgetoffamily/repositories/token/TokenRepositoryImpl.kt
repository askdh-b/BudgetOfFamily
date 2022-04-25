package rustam.urazov.budgetoffamily.repositories.token

import rustam.urazov.budgetoffamily.storage.TokenStorageService
import rustam.urazov.budgetoffamily.storage.models.AccessToken
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.repositories.token.TokenRepository

class TokenRepositoryImpl(private val tokenStorageService: TokenStorageService): TokenRepository {

    override fun saveToken(token: Token) {
        val accessToken = AccessToken(token.accessToken)

        tokenStorageService.save(accessToken)
    }

    override fun getToken() = Token(tokenStorageService.get().token)
}