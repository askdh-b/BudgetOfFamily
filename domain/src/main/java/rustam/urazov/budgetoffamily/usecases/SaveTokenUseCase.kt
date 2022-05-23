package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.Token
import rustam.urazov.budgetoffamily.repositories.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {

    suspend fun execute(token: Token) {
        tokenRepository.saveToken(token)
    }
}