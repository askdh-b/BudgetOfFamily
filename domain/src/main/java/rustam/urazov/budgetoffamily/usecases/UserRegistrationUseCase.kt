package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.NewUser
import rustam.urazov.budgetoffamily.repositories.UserRegistrationRepository

class UserRegistrationUseCase(private val userRegistrationRepository: UserRegistrationRepository) {

    suspend fun execute(newUser: NewUser) = userRegistrationRepository.register(newUser)
}