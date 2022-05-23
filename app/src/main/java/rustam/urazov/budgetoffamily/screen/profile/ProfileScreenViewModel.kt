package rustam.urazov.budgetoffamily.screen.profile

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.AccessToken
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.*

class ProfileScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getIncomesUseCase: GetIncomesUseCase,
    private val mapResponseToIncomeUseCase: MapResponseToIncomeUseCase,
    private val getIncomesSumUseCase: GetIncomesSumUseCase,
    private val getSpendingsUseCase: GetSpendingsUseCase,
    private val mapResponseToSpendingUseCase: MapResponseToSpendingUseCase,
    private val getSpendingsSumUseCase: GetSpendingsSumUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
) : ViewModel() {

    val balance = MutableLiveData<Float>()

    private suspend fun getIncomes(): Float {
        var sum = 0.0f
        when (val result = getIncomesUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val incomes = mapResponseToIncomeUseCase.execute(result.value as List<*>)
                sum = getIncomesSumUseCase.execute(incomes)
            }
        }
        return sum
    }

    private suspend fun getSpendings(): Float {
        var sum = 0.0f
        when (val result = getSpendingsUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val spendings = mapResponseToSpendingUseCase.execute(result.value as List<*>)
                sum = getSpendingsSumUseCase.execute(spendings)
            }
        }
        return sum
    }

    fun getBalance() = GlobalScope.launch(Dispatchers.IO) {
        balance.postValue(getBalanceUseCase.execute(getIncomes(), getSpendings()))
    }

    private suspend fun getAccessToken(): AccessToken = getAccessTokenUseCase.execute()
}