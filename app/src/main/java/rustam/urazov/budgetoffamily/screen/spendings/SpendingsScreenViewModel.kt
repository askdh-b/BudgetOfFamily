package rustam.urazov.budgetoffamily.screen.spendings

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.SpendingData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.spending.GetSpendingsUseCase
import rustam.urazov.budgetoffamily.usecases.GetUserIdUseCase
import rustam.urazov.budgetoffamily.usecases.spending.MapResponseToSpendingUseCase

class SpendingsScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getSpendingsUseCase: GetSpendingsUseCase,
    private val mapResponseToSpendingUseCase: MapResponseToSpendingUseCase
) : ViewModel() {

    val spendings = MutableLiveData<List<SpendingData>>()
    val userId = MutableLiveData<Int>()

    fun getSpendings() = GlobalScope.launch {
        when(val result = getSpendingsUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(fragmentManager, result.error?.message.toString())
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val spns = mapResponseToSpendingUseCase.execute(result.value as List<*>)
                spendings.postValue(spns)
                userId.postValue(getUserIdUseCase.execute())
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}