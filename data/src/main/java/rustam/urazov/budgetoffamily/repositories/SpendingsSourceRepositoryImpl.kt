package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.SpendingsSourceResponse
import rustam.urazov.budgetoffamily.network.safeCall

class SpendingsSourceRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : SpendingsSourceRepository {
    override suspend fun getSpendingsSources(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getSpendingsSources(accessToken.token)
        })

    override suspend fun mapToSpendingsSource(spendingsSources: List<*>): List<SpendingsSourceData> {
        val spendingsSourceData = mutableListOf<SpendingsSourceData>()
        for (sS in spendingsSources) {
            val spnSr = sS as SpendingsSourceResponse
            spendingsSourceData.add(SpendingsSourceData(
                userId = spnSr.userId,
                name = spnSr.name,
                sum = spnSr.sum,
                monthDay = spnSr.monthDay
            ))
        }
        return spendingsSourceData
    }
}