package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.SpendingsSource
import rustam.urazov.budgetoffamily.models.SpendingsSourceData

interface SpendingsSourceRepository {

    suspend fun getSpendingsSources(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun getSpendingsSource(accessToken: AccessToken, id: Int): ResultWrapper<Any>

    suspend fun addSpendingsSource(
        accessToken: AccessToken,
        spendingsSource: SpendingsSource
    ): ResultWrapper<Any>

    suspend fun editSpendingsSource(
        accessToken: AccessToken,
        spendingsSource: SpendingsSource,
        id: Int
    ): ResultWrapper<Any>

    suspend fun deleteSpendingsSource(
        accessToken: AccessToken,
        id: Int
    ): ResultWrapper<Any>

    suspend fun mapToSpendingsSource(spendingsSources: List<*>): List<SpendingsSourceData>
}