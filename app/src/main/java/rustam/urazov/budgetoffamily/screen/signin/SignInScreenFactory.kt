package rustam.urazov.budgetoffamily.screen.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.data.network.API
import rustam.urazov.data.repositories.TokenRepositoryImpl
import rustam.urazov.data.repositories.UserAuthorizationRepositoryImpl
import rustam.urazov.data.storage.TokenStorageServiceImpl
import rustam.urazov.domain.usecases.SaveTokenUseCase
import rustam.urazov.domain.usecases.UserAuthorizationUseCase

class SignInScreenFactory(context: Context): ViewModelProvider.Factory {

    private val dispatcher = Dispatchers.IO

    private val tokenStorageService by lazy {
        TokenStorageServiceImpl(context)
    }
    private val tokenRepository by lazy {
        TokenRepositoryImpl(tokenStorageService)
    }
    private val saveTokenUseCase by lazy {
        SaveTokenUseCase(tokenRepository)
    }

    private val service = API.mInstance.service
    private val userAuthorizationRepository by lazy {
        UserAuthorizationRepositoryImpl(service, dispatcher)
    }
    private val userAuthorizationUseCase by lazy {
        UserAuthorizationUseCase(userAuthorizationRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInScreenViewModel(userAuthorizationUseCase, saveTokenUseCase) as T
    }
}