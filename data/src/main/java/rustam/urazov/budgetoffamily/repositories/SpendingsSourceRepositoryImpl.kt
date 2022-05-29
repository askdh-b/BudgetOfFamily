package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.SpendingsSource
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.SpendingsSourceBody
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

    override suspend fun getSpendingsSource(accessToken: AccessToken, id: Int): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getSpendingsSource(accessToken.token, id)
        })

    override suspend fun addSpendingsSource(
        accessToken: AccessToken,
        spendingsSource: SpendingsSource
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.addSpendingsSource(
            accessToken.token, SpendingsSourceBody(
                name = spendingsSource.name,
                sum = spendingsSource.sum,
                monthDay = spendingsSource.monthDay
            )
        )
    })

    override suspend fun editSpendingsSource(
        accessToken: AccessToken,
        spendingsSource: SpendingsSource,
        id: Int
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.editSpendingsSource(
            accessToken.token, id, SpendingsSourceBody(
                name = spendingsSource.name,
                sum = spendingsSource.sum,
                monthDay = spendingsSource.monthDay
            )
        )
    })

    override suspend fun deleteSpendingsSource(
        accessToken: AccessToken,
        id: Int
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.deleteSpendingsSource(accessToken.token, id)
    })

    override suspend fun mapToSpendingsSource(spendingsSources: List<*>): List<SpendingsSourceData> {
        val spendingsSourceData = mutableListOf<SpendingsSourceData>()
        for (sS in spendingsSources) {
            val spnSr = sS as SpendingsSourceResponse
            spendingsSourceData.add(
                SpendingsSourceData(
                    id = spnSr.id,
                    userId = spnSr.userId,
                    name = spnSr.name,
                    sum = spnSr.sum,
                    monthDay = spnSr.monthDay
                )
            )
        }
        return spendingsSourceData
    }
}