package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.UserRepository

class SearchUseCase(private val userRepository: UserRepository) {

    suspend fun execute(accessToken: AccessToken, q: String) = userRepository.search(accessToken, q)
}