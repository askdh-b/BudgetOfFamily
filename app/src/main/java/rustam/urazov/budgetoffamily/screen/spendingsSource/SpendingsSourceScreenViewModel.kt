package rustam.urazov.budgetoffamily.screen.spendingsSource

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.SpendingsSourceData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.DeleteSpendingsSourceUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.GetSpendingsSourcesUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.MapResponseToSpendingsSourceUseCase

class SpendingsSourceScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getSpendingsSourcesUseCase: GetSpendingsSourcesUseCase,
    private val mapResponseToSpendingsSourceUseCase: MapResponseToSpendingsSourceUseCase,
    private val deleteSpendingsSourceUseCase: DeleteSpendingsSourceUseCase
) : ViewModel() {
    val spendingsSources = MutableLiveData<List<SpendingsSourceData>>()

    fun getSpendingsSources() = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getSpendingsSourcesUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val spSs = mapResponseToSpendingsSourceUseCase.execute(result.value as List<*>)
                spendingsSources.postValue(spSs)
            }
        }
    }

    fun deleteSpendingsSource(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = deleteSpendingsSourceUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                getSpendingsSources()
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}