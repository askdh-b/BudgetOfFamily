package rustam.urazov.budgetoffamily.screen.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.token.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.UserRegistrationRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.storage.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.register.UserRegistrationUseCase

class SignUpScreenFactory(context: Context) : ViewModelProvider.Factory {

    private val service = API.mInstance.networkService
    private val dispatcher = Dispatchers.IO

    private val userRegistrationRepository by lazy {
        UserRegistrationRepositoryImpl(service, dispatcher)
    }
    private val userRegistrationUseCase by lazy {
        UserRegistrationUseCase(userRegistrationRepository)
    }

    private val tokenStorageServiceImpl by lazy {
        StorageServiceImpl(context)
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