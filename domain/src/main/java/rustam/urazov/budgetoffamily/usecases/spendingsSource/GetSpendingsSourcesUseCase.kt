package rustam.urazov.budgetoffamily.usecases.spendingsSource

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.SpendingsSourceRepository

class GetSpendingsSourcesUseCase(private val spendingsSourceRepository: SpendingsSourceRepository) {

    suspend fun execute(accessToken: AccessToken) =
        spendingsSourceRepository.getSpendingsSources(accessToken)
}