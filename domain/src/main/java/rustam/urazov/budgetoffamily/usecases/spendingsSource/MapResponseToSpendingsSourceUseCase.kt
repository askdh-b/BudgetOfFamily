package rustam.urazov.budgetoffamily.usecases.spendingsSource

import rustam.urazov.budgetoffamily.repositories.SpendingsSourceRepository

class MapResponseToSpendingsSourceUseCase(private val spendingsSourceRepository: SpendingsSourceRepository) {

    suspend fun execute(spendingsSources: List<*>) = spendingsSourceRepository.mapToSpendingsSource(spendingsSources)
}