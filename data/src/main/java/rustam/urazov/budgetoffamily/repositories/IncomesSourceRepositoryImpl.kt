package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.IncomesSourceData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.IncomesSourceResponse
import rustam.urazov.budgetoffamily.network.safeCall

class IncomesSourceRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : IncomesSourceRepository{
    override suspend fun getIncomesSources(accessToken: AccessToken): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.getIncomesSources(accessToken.token)
    })

    override suspend fun mapToIncomesSource(incomesSources: List<*>): List<IncomesSourceData> {
        val incomesSourceData = mutableListOf<IncomesSourceData>()
        for (iS in incomesSources) {
            val incSr = iS as IncomesSourceResponse
            incomesSourceData.add(IncomesSourceData(
                userId = incSr.userId,
                name = incSr.name,
                sum = incSr.sum,
                monthDay = incSr.monthDay
            ))
        }
        return incomesSourceData
    }
}