package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.TokenRepository

class GetAccessTokenUseCase(private val tokenRepository: TokenRepository) {

    suspend fun execute(): AccessToken = tokenRepository.getAccessToken()
}