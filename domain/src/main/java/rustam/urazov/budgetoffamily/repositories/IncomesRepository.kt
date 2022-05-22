package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken

interface IncomesRepository {

    suspend fun getIncomes(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun addIncome(): ResultWrapper<Any>
}