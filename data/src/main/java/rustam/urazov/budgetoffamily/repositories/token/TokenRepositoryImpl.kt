package rustam.urazov.budgetoffamily.repositories.token

import rustam.urazov.budgetoffamily.models.token.AccessToken
import rustam.urazov.budgetoffamily.models.token.RefreshToken
import rustam.urazov.budgetoffamily.storage.StorageService
import rustam.urazov.budgetoffamily.storage.models.StorageAccessToken
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.storage.models.StorageRefreshToken
import rustam.urazov.budgetoffamily.storage.models.StorageToken

class TokenRepositoryImpl(private val storageService: StorageService) : TokenRepository {

    override fun saveToken(token: Token) = storageService.saveTokens(
        StorageToken(
            storageAccessToken = StorageAccessToken(token.accessToken),
            storageRefreshToken = StorageRefreshToken(token.refreshToken)
        )
    )

    override fun getAccessToken(): AccessToken = AccessToken(
        token = storageService.getAccessToken().token
    )

    override fun getRefreshToken(): RefreshToken = RefreshToken(
        token = storageService.getRefreshToken().token
    )
}