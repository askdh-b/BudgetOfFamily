package rustam.urazov.budgetoffamily.usecases.spending

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Spending
import rustam.urazov.budgetoffamily.repositories.SpendingRepository

class AddSpendingUseCase(private val spendingRepository: SpendingRepository) {

    suspend fun execute(accessToken: AccessToken, spending: Spending) =
        spendingRepository.addSpending(accessToken, spending)
}