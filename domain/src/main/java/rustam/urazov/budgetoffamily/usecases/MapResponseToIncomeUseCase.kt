package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.IncomeData
import rustam.urazov.budgetoffamily.repositories.IncomeRepository

class MapResponseToIncomeUseCase(private val incomeRepository: IncomeRepository) {

    suspend fun execute(incomes: List<*>): List<IncomeData> = incomeRepository.mapToIncome(incomes)
}