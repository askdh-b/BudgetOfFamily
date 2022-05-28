package rustam.urazov.budgetoffamily.screen.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.InvitationRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.UserRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.MapResponseToUserUseCase
import rustam.urazov.budgetoffamily.usecases.SearchUseCase
import rustam.urazov.budgetoffamily.usecases.invitation.SendInvitationUseCase

class SearchScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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
    private val sendInvitationUseCase by lazy {
        SendInvitationUseCase(invitationRepository)
    }

    private val userRepository by lazy {
        UserRepositoryImpl(networkService, dispatcher)
    }
    private val searchUseCase by lazy {
        SearchUseCase(userRepository)
    }
    private val mapResponseToUserUseCase by lazy {
        MapResponseToUserUseCase(userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = SearchScreenViewModel(
        fragmentManager,
        getAccessTokenUseCase,
        searchUseCase,
        mapResponseToUserUseCase,
        sendInvitationUseCase
    ) as T
}