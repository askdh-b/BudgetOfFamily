package rustam.urazov.budgetoffamily.usecases.spending

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.SpendingRepository

class GetSpendingsUseCase(private val spendingRepository: SpendingRepository) {

    suspend fun execute(accessToken: AccessToken) = spendingRepository.getSpendings(accessToken)
}