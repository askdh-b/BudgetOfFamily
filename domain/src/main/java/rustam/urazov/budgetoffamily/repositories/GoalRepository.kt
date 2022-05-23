package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.GoalData

interface GoalRepository {

    suspend fun getGoals(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun mapToGoal(goals: List<*>): List<GoalData>
}