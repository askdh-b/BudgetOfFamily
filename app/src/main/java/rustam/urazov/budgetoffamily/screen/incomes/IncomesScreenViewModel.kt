package rustam.urazov.budgetoffamily.screen.incomes

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.IncomeData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.income.GetIncomesUseCase
import rustam.urazov.budgetoffamily.usecases.GetUserIdUseCase
import rustam.urazov.budgetoffamily.usecases.income.MapResponseToIncomeUseCase

class IncomesScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getIncomesUseCase: GetIncomesUseCase,
    private val mapResponseToIncomeUseCase: MapResponseToIncomeUseCase
): ViewModel() {

    val incomes = MutableLiveData<List<IncomeData>>()
    val userId = MutableLiveData<Int>()

    fun getIncomes() = GlobalScope.launch {
        when(val result = getIncomesUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(fragmentManager, result.error?.message.toString())
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val incs = mapResponseToIncomeUseCase.execute(result.value as List<*>)
                incomes.postValue(incs)
                userId.postValue(getUserIdUseCase.execute())
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}