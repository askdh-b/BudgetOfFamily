package rustam.urazov.budgetoffamily.usecases.spending

import rustam.urazov.budgetoffamily.models.SpendingData

class GetSpendingsSumUseCase {
    fun execute(spendings: List<SpendingData>): Float {
        var sum = 0.0f
        for (s in spendings) {
            sum += s.sum
        }
        return sum
    }
}