package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.GoalResponse
import rustam.urazov.budgetoffamily.network.safeCall

class GoalRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
)  : GoalRepository {
    override suspend fun getGoals(accessToken: AccessToken): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.getGoals(accessToken.token)
    })

    override suspend fun mapToGoal(goals: List<*>): List<GoalData> {
        val goalData = mutableListOf<GoalData>()
        for (g in goals) {
            val gs = g as GoalResponse
            goalData.add(GoalData(
                userId = gs.userId,
                name = gs.name,
                incomePercentile = gs.incomePercentile,
                actualSum = gs.actualSum,
                necessarySum = gs.necessarySum
            ))
        }
        return goalData
    }
}