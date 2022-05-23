package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.repositories.SpendingsRepository

class MapResponseToSpendingUseCase(private val spendingsRepository: SpendingsRepository) {

    suspend fun execute(spendings: List<*>) = spendingsRepository.mapToSpending(spendings)
}