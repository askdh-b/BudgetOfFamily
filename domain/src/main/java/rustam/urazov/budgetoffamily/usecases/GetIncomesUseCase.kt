package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.IncomeRepository

class GetIncomesUseCase(private val incomeRepository: IncomeRepository) {

    suspend fun execute(accessToken: AccessToken) = incomeRepository.getIncomes(accessToken)
}