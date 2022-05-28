package rustam.urazov.budgetoffamily.usecases.income

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Income
import rustam.urazov.budgetoffamily.repositories.IncomeRepository

class AddIncomeUseCase(private val incomeRepository: IncomeRepository) {

    suspend fun execute(accessToken: AccessToken, income: Income) =
        incomeRepository.addIncome(accessToken, income)
}