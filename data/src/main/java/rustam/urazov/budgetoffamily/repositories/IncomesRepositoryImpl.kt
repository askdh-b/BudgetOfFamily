package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.safeCall

class IncomesRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : IncomesRepository {
    override suspend fun getIncomes(accessToken: AccessToken): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.getIncomes(accessToken.token)
    })

    override suspend fun addIncome(accessToken: AccessToken, ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.addIncome()
    })
}