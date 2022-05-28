package rustam.urazov.budgetoffamily.usecases.income

import rustam.urazov.budgetoffamily.models.IncomeData

class GetIncomesSumUseCase {

    fun execute(incomes: List<IncomeData>): Float {
        var sum = 0.0f
        for (i in incomes) {
            sum += i.sum
        }
        return sum
    }
}