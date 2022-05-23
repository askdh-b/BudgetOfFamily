package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Income
import rustam.urazov.budgetoffamily.models.IncomeData

interface IncomesRepository {

    suspend fun getIncomes(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun addIncome(accessToken: AccessToken, income: Income): ResultWrapper<Any>

    suspend fun mapToIncome(incomes: List<*>): List<IncomeData>
}