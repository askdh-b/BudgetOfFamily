package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.repositories.IncomesSourceRepository

class MapResponseToIncomesSourceUseCase(private val incomesSourceRepository: IncomesSourceRepository) {

    suspend fun execute(incomesSources: List<*>) = incomesSourceRepository.mapToIncomesSource(incomesSources)
}