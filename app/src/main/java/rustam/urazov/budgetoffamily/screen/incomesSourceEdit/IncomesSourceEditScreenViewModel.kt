package rustam.urazov.budgetoffamily.screen.incomesSourceEdit

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
import rustam.urazov.budgetoffamily.usecases.incomesSource.EditIncomesSourceUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.GetIncomesSourceUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.MapResponseToIncomesSourceUseCase

class IncomesSourceEditScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getIncomesSourceUseCase: GetIncomesSourceUseCase,
    private val mapResponseToIncomesSourceUseCase: MapResponseToIncomesSourceUseCase,
    private val editIncomesSourceUseCase: EditIncomesSourceUseCase
) : ViewModel() {

    val incomesSource = MutableLiveData<IncomesSourceData>()
    val success = MutableLiveData<Boolean>()

    fun getIncomesSource(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getIncomesSourceUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val inS = mapResponseToIncomesSourceUseCase.execute(listOf(result.value) as List <*>)
                incomesSource.postValue(inS[0])
            }
        }
    }

    fun editIncomesSource(incomesSource: IncomesSource, id: Int) =
        GlobalScope.launch(Dispatchers.IO) {
            when (val result =
                editIncomesSourceUseCase.execute(getAccessToken(), incomesSource, id)) {
                is ResultWrapper.Error -> showErrorDialog(
                    fragmentManager,
                    result.error?.message.toString()
                )
                ResultWrapper.NetworkError -> showErrorDialog(
                    fragmentManager,
                    "Ошибка с сетью. Попробуйте позже."
                )
                is ResultWrapper.Success -> {
                    success.postValue(true)
                }
            }
        }

    fun showError() = showErrorDialog(
        fragmentManager,
        "Введите корректные данные"
    )

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}