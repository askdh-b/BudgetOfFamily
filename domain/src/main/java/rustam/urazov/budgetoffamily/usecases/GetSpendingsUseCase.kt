package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.SpendingsRepository

class GetSpendingsUseCase(private val spendingsRepository: SpendingsRepository) {

    suspend fun execute(accessToken: AccessToken) = spendingsRepository.getSpendings(accessToken)
}