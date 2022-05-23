package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.SpendingsSourceData

interface SpendingsSourceRepository {

    suspend fun getSpendingsSources(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToSpendingsSource(spendingsSources: List<*>): List<SpendingsSourceData>
}