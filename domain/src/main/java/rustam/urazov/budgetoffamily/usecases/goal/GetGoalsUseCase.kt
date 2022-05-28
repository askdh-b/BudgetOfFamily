package rustam.urazov.budgetoffamily.usecases.goal

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.repositories.GoalRepository

class GetGoalsUseCase(private val goalRepository: GoalRepository) {

    suspend fun execute(accessToken: AccessToken) = goalRepository.getGoals(accessToken)
}