package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.IncomesRepository

class GetIncomesUseCase(private val incomesRepository: IncomesRepository) {

    suspend fun execute(accessToken: AccessToken) = incomesRepository.getIncomes(accessToken)
}