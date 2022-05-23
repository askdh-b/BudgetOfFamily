package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.IncomesSourceData

class GetIncomesSourcesSumUseCase {

    fun execute(incomesSources: List<IncomesSourceData>): Float {
        var sum = 0.0f
        for (iS in incomesSources) {
            sum += iS.sum
        }
        return sum
    }
}