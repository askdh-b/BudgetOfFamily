package rustam.urazov.budgetoffamily.usecases.goal

import rustam.urazov.budgetoffamily.models.GoalData

class GetCompletedGoalsUseCase {

    fun execute(goals: List<GoalData>): List<GoalData> {
        val completedGoals = mutableListOf<GoalData>()
        for (g in goals) {
            if (g.actualSum >= g.necessarySum) completedGoals.add(g)
        }
        return completedGoals
    }
}