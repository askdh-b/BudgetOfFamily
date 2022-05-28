package rustam.urazov.budgetoffamily.usecases.goal

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.GoalRepository

class GetGoalUseCase(private val goalRepository: GoalRepository) {

    suspend fun execute(accessToken: AccessToken, id: Int) = goalRepository.getGoal(accessToken, id)
}