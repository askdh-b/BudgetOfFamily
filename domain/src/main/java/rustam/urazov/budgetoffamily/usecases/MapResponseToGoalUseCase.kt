package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.GoalRepository

class MapResponseToGoalUseCase(private val goalRepository: GoalRepository) {

    suspend fun execute(goals: List<*>) = goalRepository.mapToGoal(goals)
}