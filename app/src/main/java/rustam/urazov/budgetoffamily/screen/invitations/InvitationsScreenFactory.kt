package rustam.urazov.budgetoffamily.screen.invitations

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.InvitationRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.*

class InvitationsScreenFactory(private val context: Context) : ViewModelProvider.Factory {

    private val fragmentManager = (context as MainActivity).supportFragmentManager
    private val dispatcher = Dispatchers.IO
    private val networkService = API.mInstance.networkService
    private val storageService by lazy {
        StorageServiceImpl(context)
    }

    private val tokenRepository by lazy {
        TokenRepositoryImpl(storageService)
    }
    private val getAccessTokenUseCase by lazy {
        GetAccessTokenUseCase(tokenRepository)
    }

    private val invitationRepository by lazy {
        InvitationRepositoryImpl(networkService, dispatcher)
    }
    private val getInvitationsUseCase by lazy {
        GetInvitationsUseCase(invitationRepository)
    }
    private val mapResponseToInvitationUseCase by lazy {
        MapResponseToInvitationUseCase(invitationRepository)
    }
    private val acceptInvitationUseCase by lazy {
        AcceptInvitationUseCase(invitationRepository)
    }
    private val rejectInvitationUseCase by lazy {
        RejectInvitationUseCase(invitationRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = InvitationsScreenViewModel(
        fragmentManager,
        getInvitationsUseCase,
        getAccessTokenUseCase,
        mapResponseToInvitationUseCase,
        acceptInvitationUseCase,
        rejectInvitationUseCase
    ) as T
}