package rustam.urazov.budgetoffamily.usecases.goal

import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.models.GoalForEdit
import rustam.urazov.budgetoffamily.repositories.GoalRepository

class EditGoalUseCase(private val goalRepository: GoalRepository) {

    suspend fun execute(accessToken: AccessToken, id: Int, goalForEdit: GoalForEdit) =
        goalRepository.editGoal(accessToken, id, goalForEdit)
}