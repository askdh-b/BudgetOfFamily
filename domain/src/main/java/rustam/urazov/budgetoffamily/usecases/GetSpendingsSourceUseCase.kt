package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.SpendingsSourceRepository

class GetSpendingsSourceUseCase(private val spendingsSourceRepository: SpendingsSourceRepository) {

    suspend fun execute(accessToken: AccessToken) = spendingsSourceRepository.getSpendingsSources(accessToken)
}