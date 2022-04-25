package rustam.urazov.budgetoffamily.usecases.storage

import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.repositories.token.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {

    fun execute(token: Token) {
        tokenRepository.saveToken(token)
    }
}