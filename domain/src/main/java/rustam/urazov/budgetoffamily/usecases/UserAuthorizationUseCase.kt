package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.UserAuthData
import rustam.urazov.budgetoffamily.repositories.UserAuthorizationRepository

class UserAuthorizationUseCase(private val userAuthorizationRepository: UserAuthorizationRepository) {

    suspend fun execute(userAuthData: UserAuthData) =
        userAuthorizationRepository.authorize(userAuthData)
}