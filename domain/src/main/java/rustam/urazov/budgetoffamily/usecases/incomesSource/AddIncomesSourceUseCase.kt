package rustam.urazov.budgetoffamily.usecases.incomesSource

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.IncomesSource
import rustam.urazov.budgetoffamily.repositories.IncomesSourceRepository

class AddIncomesSourceUseCase(private val incomesSourceRepository: IncomesSourceRepository) {

    suspend fun execute(accessToken: AccessToken, incomesSource: IncomesSource) =
        incomesSourceRepository.addIncomesSource(accessToken, incomesSource)
}