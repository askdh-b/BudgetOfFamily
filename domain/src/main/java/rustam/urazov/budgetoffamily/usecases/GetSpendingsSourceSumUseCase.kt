package rustam.urazov.budgetoffamily.usecases

import rustam.urazov.budgetoffamily.models.SpendingsSourceData

class GetSpendingsSourceSumUseCase {

    fun execute(spendingsSource: List<SpendingsSourceData>): Float {
        var sum = 0.0f
        for (sS in spendingsSource) {
            sum += sS.sum
        }
        return sum
    }
}