package rustam.urazov.budgetoffamily.usecases.register

import rustam.urazov.budgetoffamily.models.user.NewUser
import rustam.urazov.budgetoffamily.repositories.register.UserRegistrationRepository

class UserRegistrationUseCase(private val userRegistrationRepository: UserRegistrationRepository) {

    suspend fun execute(newUser: NewUser) = userRegistrationRepository.register(newUser)
}