package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.IncomesSource
import rustam.urazov.budgetoffamily.models.IncomesSourceData

interface IncomesSourceRepository {

    suspend fun getIncomesSources(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun getIncomesSource(accessToken: AccessToken, id: Int): ResultWrapper<Any>

    suspend fun addIncomesSource(
        accessToken: AccessToken,
        incomesSource: IncomesSource,
    ): ResultWrapper<Any>

    suspend fun editIncomesSource(
        accessToken: AccessToken,
        incomesSource: IncomesSource,
        id: Int
    ): ResultWrapper<Any>

    suspend fun deleteIncomesSource(
        accessToken: AccessToken,
        id: Int
    ): ResultWrapper<Any>

    suspend fun mapToIncomesSource(incomesSources: List<*>): List<IncomesSourceData>
}