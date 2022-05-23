package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.GoalData

class GetCurrentGoalsUseCase {

    fun execute(goals: List<GoalData>): List<GoalData> {
        val currentGoals = mutableListOf<GoalData>()
        for (g in goals) {
            if (g.actualSum < g.necessarySum) currentGoals.add(g)
        }
        return currentGoals
    }
}