package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.RefreshToken
import rustam.urazov.budgetoffamily.storage.StorageService
import rustam.urazov.budgetoffamily.storage.models.StorageAccessToken
import rustam.urazov.budgetoffamily.models.Token
import rustam.urazov.budgetoffamily.storage.models.StorageRefreshToken
import rustam.urazov.budgetoffamily.storage.models.StorageToken

class TokenRepositoryImpl(private val storageService: StorageService) : TokenRepository {

    override suspend fun saveToken(token: Token) = storageService.saveTokens(
        StorageToken(
            userId = token.userId,
            storageAccessToken = StorageAccessToken(token.accessToken),
            storageRefreshToken = StorageRefreshToken(token.refreshToken)
        )
    )

    override suspend fun getAccessToken(): AccessToken = AccessToken(
        token = storageService.getAccessToken().token
    )

    override suspend fun getRefreshToken(): RefreshToken = RefreshToken(
        token = storageService.getRefreshToken().token
    )

    override suspend fun getUserId(): Int = storageService.getUserId()
}