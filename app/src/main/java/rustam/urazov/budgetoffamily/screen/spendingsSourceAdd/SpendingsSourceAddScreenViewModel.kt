package rustam.urazov.budgetoffamily.screen.spendingsSourceAdd

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.SpendingsSource
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.AddSpendingsSourceUseCase

class SpendingsSourceAddScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val addSpendingsSourceUseCase: AddSpendingsSourceUseCase
) : ViewModel() {

    val success = MutableLiveData<Boolean>()

    fun addSpendingsSource(spendingsSource: SpendingsSource) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = addSpendingsSourceUseCase.execute(getAccessToken(), spendingsSource)) {
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

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}