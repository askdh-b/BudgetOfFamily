package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.IncomesSource
import rustam.urazov.budgetoffamily.models.IncomesSourceData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.IncomesSourceBody
import rustam.urazov.budgetoffamily.network.models.IncomesSourceResponse
import rustam.urazov.budgetoffamily.network.safeCall

class IncomesSourceRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : IncomesSourceRepository {
    override suspend fun getIncomesSources(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getIncomesSources(accessToken.token)
        })

    override suspend fun getIncomesSource(accessToken: AccessToken, id: Int): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.getIncomesSource(accessToken.token, id)
    })

    override suspend fun addIncomesSource(
        accessToken: AccessToken,
        incomesSource: IncomesSource
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.addIncomesSource(
            accessToken.token, IncomesSourceBody(
                name = incomesSource.name,
                sum = incomesSource.sum,
                monthDay = incomesSource.monthDay
            )
        )
    })

    override suspend fun editIncomesSource(
        accessToken: AccessToken,
        incomesSource: IncomesSource,
        id: Int
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.editIncomesSource(
            accessToken.token, id, IncomesSourceBody(
                name = incomesSource.name,
                sum = incomesSource.sum,
                monthDay = incomesSource.monthDay
            )
        )
    })

    override suspend fun deleteIncomesSource(
        accessToken: AccessToken,
        id: Int
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.deleteIncomesSource(accessToken.token, id)
    })

    override suspend fun mapToIncomesSource(incomesSources: List<*>): List<IncomesSourceData> {
        val incomesSourceData = mutableListOf<IncomesSourceData>()
        for (iS in incomesSources) {
            val incSr = iS as IncomesSourceResponse
            incomesSourceData.add(
                IncomesSourceData(
                    id = incSr.id,
                    userId = incSr.userId,
                    name = incSr.name,
                    sum = incSr.sum,
                    monthDay = incSr.monthDay
                )
            )
        }
        return incomesSourceData
    }
}