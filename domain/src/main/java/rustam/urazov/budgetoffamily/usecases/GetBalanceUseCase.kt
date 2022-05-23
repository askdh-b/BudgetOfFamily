package rustam.urazov.budgetoffamily.usecases

class GetBalanceUseCase {

    fun execute(incomes: Float, spendings: Float) = incomes - spendings
}