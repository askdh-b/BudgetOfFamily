package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Goal
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.models.GoalForEdit
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.GoalBody
import rustam.urazov.budgetoffamily.network.models.GoalBodyForEdit
import rustam.urazov.budgetoffamily.network.models.GoalResponse
import rustam.urazov.budgetoffamily.network.safeCall

class GoalRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : GoalRepository {

    override suspend fun getGoals(accessToken: AccessToken): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getGoals(accessToken.token)
        })

    override suspend fun getGoal(accessToken: AccessToken, id: Int): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.getGoal(accessToken.token, id)
        })

    override suspend fun addGoal(accessToken: AccessToken, goal: Goal): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.addGoal(
                accessToken.token, GoalBody(
                    name = goal.name,
                    incomePercentile = goal.incomePercentile,
                    sum = goal.sum
                )
            )
        })

    override suspend fun editGoal(
        accessToken: AccessToken,
        id: Int,
        goalForEdit: GoalForEdit
    ): ResultWrapper<Any> = safeCall(dispatcher, call = {
        networkService.editGoal(
            accessToken.token, id, GoalBodyForEdit(
                name = goalForEdit.name,
                incomePercentile = goalForEdit.incomePercentile,
                progress = goalForEdit.progress,
                sum = goalForEdit.sum
            )
        )
    })

    override suspend fun deleteGoal(accessToken: AccessToken, id: Int): ResultWrapper<Any> =
        safeCall(dispatcher, call = {
            networkService.deleteGoal(accessToken.token, id)
        })

    override suspend fun mapToGoal(goals: List<*>): List<GoalData> {
        val goalData = mutableListOf<GoalData>()
        for (g in goals) {
            val gs = g as GoalResponse
            goalData.add(
                GoalData(
                    id = gs.id,
                    userId = gs.userId,
                    name = gs.name,
                    incomePercentile = gs.incomePercentile,
                    actualSum = gs.actualSum,
                    necessarySum = gs.necessarySum
                )
            )
        }
        return goalData
    }
}