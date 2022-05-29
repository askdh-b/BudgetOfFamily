package rustam.urazov.budgetoffamily.screen.spendingsSourceEdit

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.SpendingsSource
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.EditSpendingsSourceUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.GetSpendingsSourceUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.MapResponseToSpendingsSourceUseCase

class SpendingsSourceEditScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getSpendingsSourceUseCase: GetSpendingsSourceUseCase,
    private val mapResponseToSpendingsSourceUseCase: MapResponseToSpendingsSourceUseCase,
    private val editSpendingsSourceUseCase: EditSpendingsSourceUseCase
) : ViewModel() {

    val spendingsSource = MutableLiveData<SpendingsSourceData>()
    val success = MutableLiveData<Boolean>()

    fun getSpendingsSource(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getSpendingsSourceUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val inS =
                    mapResponseToSpendingsSourceUseCase.execute(listOf(result.value) as List<*>)
                spendingsSource.postValue(inS[0])
            }
        }
    }

    fun editSpendingsSource(spendingsSource: SpendingsSource, id: Int) =
        GlobalScope.launch(Dispatchers.IO) {
            when (val result =
                editSpendingsSourceUseCase.execute(getAccessToken(), spendingsSource, id)) {
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