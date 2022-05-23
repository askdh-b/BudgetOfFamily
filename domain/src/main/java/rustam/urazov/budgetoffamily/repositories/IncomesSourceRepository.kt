package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.IncomesSourceData

interface IncomesSourceRepository {

    suspend fun getIncomesSources(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToIncomesSource(incomesSources: List<*>): List<IncomesSourceData>
}