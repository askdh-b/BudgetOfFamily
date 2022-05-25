package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Income
import rustam.urazov.budgetoffamily.models.IncomeData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.IncomeBody
import rustam.urazov.budgetoffamily.network.models.IncomeResponse
import rustam.urazov.budgetoffamily.network.safeCall

class IncomeRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : IncomeRepository {
    override suspend fun getIncomes(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getIncomes(accessToken.token)
        })

    override suspend fun addIncome(accessToken: AccessToken, income: Income): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.addIncome(
                accessToken.token, IncomeBody(
                    sum = income.sum,
                    name = income.name
                )
            )
        })

    override suspend fun mapToIncome(incomes: List<*>): List<IncomeData> {
        val incomesData = mutableListOf<IncomeData>()
        for (i in incomes) {
            val inc = i as IncomeResponse
            incomesData.add(IncomeData(
                userId = inc.userId,
                firstName = inc.firstName,
                sum = inc.sum,
                name = inc.name,
                creationDate = inc.creationDate
            ))
        }
        return incomesData
    }
}