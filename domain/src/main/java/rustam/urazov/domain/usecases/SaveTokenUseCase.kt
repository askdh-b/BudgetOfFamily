package rustam.urazov.domain.usecases

import rustam.urazov.domain.models.Token
import rustam.urazov.domain.repositories.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {

    fun execute(token: Token) {
        tokenRepository.saveToken(token)
    }
}