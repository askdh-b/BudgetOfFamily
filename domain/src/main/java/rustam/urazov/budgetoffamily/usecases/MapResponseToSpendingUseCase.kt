package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.repositories.SpendingRepository

class MapResponseToSpendingUseCase(private val spendingRepository: SpendingRepository) {

    suspend fun execute(spendings: List<*>) = spendingRepository.mapToSpending(spendings)
}