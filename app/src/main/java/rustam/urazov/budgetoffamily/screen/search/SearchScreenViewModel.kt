package rustam.urazov.budgetoffamily.screen.search

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.Invitation
import rustam.urazov.budgetoffamily.models.UserData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.screen.showInfoDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.MapResponseToUserUseCase
import rustam.urazov.budgetoffamily.usecases.SearchUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.SendInvitationUseCase

class SearchScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val searchUseCase: SearchUseCase,
    private val mapResponseToUserUseCase: MapResponseToUserUseCase,
    private val sendInvitationUseCase: SendInvitationUseCase
) : ViewModel() {

    val users = MutableLiveData<List<UserData>>()
    val success = MutableLiveData<Boolean>()

    fun getUsers(q: String) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = searchUseCase.execute(getAccessToken(), q)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val us = mapResponseToUserUseCase.execute(result.value as List<*>)
                users.postValue(us)
            }
        }
    }

    fun sendInvitation(invitation: Invitation) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = sendInvitationUseCase.execute(getAccessToken(), invitation)) {
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
        showInfoDialog(fragmentManager, "Приглашение отправлено", "Приглашение отправлено")
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}