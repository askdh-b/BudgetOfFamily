package rustam.urazov.budgetoffamily.screen.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.UserRegistrationRepositoryImpl
import rustam.urazov.budgetoffamily.storage.TokenStorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.UserRegistrationUseCase

class SignUpScreenFactory(context: Context) : ViewModelProvider.Factory {

    private val service = API.mInstance.service
    private val dispatcher = Dispatchers.IO

    private val userRegistrationRepository by lazy {
        UserRegistrationRepositoryImpl(service, dispatcher)
    }
    private val userRegistrationUseCase by lazy {
        UserRegistrationUseCase(userRegistrationRepository)
    }

    private val tokenStorageServiceImpl by lazy {
        TokenStorageServiceImpl(context)
    }
    private val tokenRepository by lazy {
        TokenRepositoryImpl(tokenStorageServiceImpl)
    }
    private val saveTokenUseCase by lazy {
        SaveTokenUseCase(tokenRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpScreenViewModel(userRegistrationUseCase, saveTokenUseCase) as T
    }
}