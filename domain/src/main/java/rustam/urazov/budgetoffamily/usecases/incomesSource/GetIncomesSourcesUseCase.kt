package rustam.urazov.budgetoffamily.usecases.incomesSource

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.IncomesSourceRepository

class GetIncomesSourcesUseCase(private val incomesSourceRepository: IncomesSourceRepository) {

    suspend fun execute(accessToken: AccessToken) = incomesSourceRepository.getIncomesSources(accessToken)
}