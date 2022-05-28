package rustam.urazov.budgetoffamily.usecases.goal

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.Goal
import rustam.urazov.budgetoffamily.repositories.GoalRepository

class AddGoalUseCase(private val goalRepository: GoalRepository) {

    suspend fun execute(accessToken: AccessToken, goal: Goal) = goalRepository.addGoal(accessToken, goal)
}