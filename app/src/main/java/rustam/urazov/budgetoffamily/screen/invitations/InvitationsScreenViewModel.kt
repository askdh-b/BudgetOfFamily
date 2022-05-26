package rustam.urazov.budgetoffamily.screen.invitations

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.InvitationData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.*
import rustam.urazov.budgetoffamily.usecases.invitation.AcceptInvitationUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.GetInvitationsUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.MapResponseToInvitationUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.RejectInvitationUseCase

class InvitationsScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getInvitationsUseCase: GetInvitationsUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val mapResponseToInvitationUseCase: MapResponseToInvitationUseCase,
    private val acceptInvitationUseCase: AcceptInvitationUseCase,
    private val rejectInvitationUseCase: RejectInvitationUseCase
) : ViewModel() {

    val invitations = MutableLiveData<List<InvitationData>>()

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
                val invs = mapResponseToInvitationUseCase.execute(result.value as List<*>)
                invitations.postValue(invs)
            }
        }
    }

    fun acceptInvitation(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = acceptInvitationUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                getInvitations()
            }
        }
    }

    fun rejectInvitation(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = rejectInvitationUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                getInvitations()
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}