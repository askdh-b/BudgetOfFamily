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
import rustam.urazov.budgetoffamily.usecases.incomesSource.GetIncomesSourcesSumUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.GetIncomesSourcesUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.MapResponseToIncomesSourceUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.GetInvitationsCountUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.GetInvitationsUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.MapResponseToInvitationUseCase

class ProfileScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getIncomesUseCase: GetIncomesUseCase,
    private val mapResponseToIncomeUseCase: MapResponseToIncomeUseCase,
    private val getIncomesSumUseCase: GetIncomesSumUseCase,
    private val getSpendingsUseCase: GetSpendingsUseCase,
    private val mapResponseToSpendingUseCase: MapResponseToSpendingUseCase,
    private val getSpendingsSumUseCase: GetSpendingsSumUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getIncomesSourcesUseCase: GetIncomesSourcesUseCase,
    private val mapResponseToIncomesSourceUseCase: MapResponseToIncomesSourceUseCase,
    private val getIncomesSourcesSumUseCase: GetIncomesSourcesSumUseCase,
    private val getSpendingsSourceUseCase: GetSpendingsSourceUseCase,
    private val mapResponseToSpendingsSourceUseCase: MapResponseToSpendingsSourceUseCase,
    private val getSpendingsSourceSumUseCase: GetSpendingsSourceSumUseCase,
    private val getInvitationsUseCase: GetInvitationsUseCase,
    private val mapResponseToInvitationUseCase: MapResponseToInvitationUseCase,
    private val getInvitationsCountUseCase: GetInvitationsCountUseCase
) : ViewModel() {

    val balance = MutableLiveData<Float>()
    val incomesSourcesValue = MutableLiveData<Float>()
    val spendingsSourcesValue = MutableLiveData<Float>()
    val invitationsCount = MutableLiveData<Int>()

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
                val incomesSources = mapResponseToIncomesSourceUseCase.execute(result.value as List<*>)
                val sum = getIncomesSourcesSumUseCase.execute(incomesSources)
                incomesSourcesValue.postValue(sum)
            }
        }
    }

    fun getSpendingsSources() = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getSpendingsSourceUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val spendingsSources = mapResponseToSpendingsSourceUseCase.execute(result.value as List<*>)
                val sum = getSpendingsSourceSumUseCase.execute(spendingsSources)
                spendingsSourcesValue.postValue(sum)
            }
        }
    }

    fun getInvitations() = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getInvitationsUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val invitations = mapResponseToInvitationUseCase.execute(result.value as List<*>)
                val count = getInvitationsCountUseCase.execute(invitations)
                invitationsCount.postValue(count)
            }
        }
    }

    fun getBalance() = GlobalScope.launch(Dispatchers.IO) {
        balance.postValue(getBalanceUseCase.execute(getIncomes(), getSpendings()))
    }

    private suspend fun getAccessToken(): AccessToken = getAccessTokenUseCase.execute()
}