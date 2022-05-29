package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.UserRepository

class LeaveUseCase(private val userRepository: UserRepository) {

    suspend fun execute(accessToken: AccessToken) = userRepository.leave(accessToken)
}