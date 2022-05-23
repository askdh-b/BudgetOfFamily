package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Spending
import rustam.urazov.budgetoffamily.models.SpendingData

interface SpendingsRepository {

    suspend fun getSpendings(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun addSpending(accessToken: AccessToken, spending: Spending): ResultWrapper<Any>

    suspend fun mapToSpending(spendings: List<*>): List<SpendingData>
}