package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.repositories.TokenRepository

class GetUserIdUseCase(private val tokenRepository: TokenRepository) {

    suspend fun execute() = tokenRepository.getUserId()
}