package rustam.urazov.budgetoffamily.screen.incomesSources

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.IncomesSourceRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.incomesSource.*

class IncomesSourcesScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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

    private val incomesSourceRepository by lazy {
        IncomesSourceRepositoryImpl(networkService, dispatcher)
    }
    private val getIncomesSourceRepository by lazy {
        GetIncomesSourcesUseCase(incomesSourceRepository)
    }
    private val mapResponseToIncomesSourceUseCase by lazy {
        MapResponseToIncomesSourceUseCase(incomesSourceRepository)
    }
    private val deleteIncomesSourceUseCase by lazy {
        DeleteIncomesSourceUseCase(incomesSourceRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = IncomesSourcesScreenViewModel(
        fragmentManager,
        getAccessTokenUseCase,
        getIncomesSourceRepository,
        mapResponseToIncomesSourceUseCase,
        deleteIncomesSourceUseCase
    ) as T
}