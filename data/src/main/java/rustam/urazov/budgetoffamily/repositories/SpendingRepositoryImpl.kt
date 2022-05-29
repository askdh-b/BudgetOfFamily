package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Spending
import rustam.urazov.budgetoffamily.models.SpendingData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.SpendingBody
import rustam.urazov.budgetoffamily.network.models.SpendingResponse
import rustam.urazov.budgetoffamily.network.safeCall

class SpendingRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : SpendingRepository {

    override suspend fun getSpendings(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getSpendings(accessToken.token)
        })

    override suspend fun addSpending(
        accessToken: AccessToken,
        spending: Spending
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.addSpending(
            accessToken.token, SpendingBody(
                sum = spending.sum,
                name = spending.name
            )
        )
    })

    override suspend fun mapToSpending(spendings: List<*>): List<SpendingData> {
        val spendingsData = mutableListOf<SpendingData>()
        for (s in spendings) {
            val spn = s as SpendingResponse
            spendingsData.add(
                SpendingData(
                    userId = spn.userId,
                    firstName = spn.firstName,
                    sum = spn.sum,
                    name = spn.name,
                    creationDate = spn.creationDate
                )
            )
        }
        return spendingsData
    }
}