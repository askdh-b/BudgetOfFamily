package rustam.urazov.budgetoffamily.usecases.incomesSource

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.IncomesSourceRepository

class GetIncomesSourceUseCase(private val incomesSourceRepository: IncomesSourceRepository) {

    suspend fun execute(accessToken: AccessToken, id: Int) =
        incomesSourceRepository.getIncomesSource(accessToken, id)
}