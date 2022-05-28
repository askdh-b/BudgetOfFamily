package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.UserRepository

class MapResponseToUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(users: List<*>) = userRepository.mapToUser(users)
}