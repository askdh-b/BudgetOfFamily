package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Goal
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.models.GoalForEdit

interface GoalRepository {

    suspend fun getGoals(accessToken: AccessToken): ResultWrapper<Any>

    suspend fun getGoal(accessToken: AccessToken, id: Int): ResultWrapper<Any>

    suspend fun addGoal(accessToken: AccessToken, goal: Goal): ResultWrapper<Any>

    suspend fun editGoal(accessToken: AccessToken, id: Int, goalForEdit: GoalForEdit): ResultWrapper<Any>

    suspend fun deleteGoal(accessToken: AccessToken, id: Int): ResultWrapper<Any>

    suspend fun mapToGoal(goals: List<*>): List<GoalData>
}