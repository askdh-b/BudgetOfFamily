package rustam.urazov.budgetoffamily.screen.incomesSources

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.IncomesSource
import rustam.urazov.budgetoffamily.models.IncomesSourceData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.*

class IncomesSourcesScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getIncomesSourcesUseCase: GetIncomesSourcesUseCase,
    private val mapResponseToIncomesSourceUseCase: MapResponseToIncomesSourceUseCase,
    private val deleteIncomesSourceUseCase: DeleteIncomesSourceUseCase
) : ViewModel() {

    val incomesSources = MutableLiveData<List<IncomesSourceData>>()

    fun getIncomesSources() = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getIncomesSourcesUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val inSs = mapResponseToIncomesSourceUseCase.execute(result.value as List<*>)
                incomesSources.postValue(inSs)
            }
        }
    }

    fun deleteIncomesSource(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = deleteIncomesSourceUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                getIncomesSources()
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}