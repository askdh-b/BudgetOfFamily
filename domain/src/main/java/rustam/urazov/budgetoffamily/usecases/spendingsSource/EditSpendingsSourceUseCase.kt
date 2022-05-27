package rustam.urazov.budgetoffamily.usecases.spendingsSource

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.SpendingsSource
import rustam.urazov.budgetoffamily.repositories.SpendingsSourceRepository

class EditSpendingsSourceUseCase(private val spendingsSourceRepository: SpendingsSourceRepository) {

    suspend fun execute(accessToken: AccessToken, spendingsSource: SpendingsSource, id: Int) =
        spendingsSourceRepository.editSpendingsSource(accessToken, spendingsSource, id)
}