package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.IncomeData
import rustam.urazov.budgetoffamily.repositories.IncomesRepository

class MapResponseToIncomeUseCase(private val incomesRepository: IncomesRepository) {

    suspend fun execute(incomes: List<*>): List<IncomeData> = incomesRepository.mapToIncome(incomes)
}