package rustam.urazov.budgetoffamily.screen.spendings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.SpendingRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.*

class SpendingsScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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
    private val getUserIdUseCase by lazy {
        GetUserIdUseCase(tokenRepository)
    }

    private val spendingRepository by lazy {
        SpendingRepositoryImpl(networkService, dispatcher)
    }
    private val getSpendingUseCase by lazy {
        GetSpendingsUseCase(spendingRepository)
    }
    private val mapResponseToSpendingUseCase by lazy {
        MapResponseToSpendingUseCase(spendingRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SpendingsScreenViewModel(
            fragmentManager,
            getAccessTokenUseCase,
            getUserIdUseCase,
            getSpendingUseCase,
            mapResponseToSpendingUseCase
        ) as T
}