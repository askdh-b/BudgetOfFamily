package rustam.urazov.domain.usecases

import rustam.urazov.domain.models.UserAuthData
import rustam.urazov.domain.repositories.UserAuthorizationRepository

class UserAuthorizationUseCase(private val userAuthorizationRepository: UserAuthorizationRepository) {

    suspend fun execute(userAuthData: UserAuthData) = userAuthorizationRepository.authorize(userAuthData)
}