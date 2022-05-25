package rustam.urazov.budgetoffamily.screen.incomes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.IncomeRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepository
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.GetIncomesUseCase
import rustam.urazov.budgetoffamily.usecases.GetUserIdUseCase
import rustam.urazov.budgetoffamily.usecases.MapResponseToIncomeUseCase

class IncomesScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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

    private val incomeRepository by lazy {
        IncomeRepositoryImpl(networkService, dispatcher)
    }
    private val getIncomesUseCase by lazy {
        GetIncomesUseCase(incomeRepository)
    }
    private val mapResponseToIncomeUseCase by lazy {
        MapResponseToIncomeUseCase(incomeRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = IncomesScreenViewModel(
        fragmentManager,
        getAccessTokenUseCase,
        getUserIdUseCase,
        getIncomesUseCase,
        mapResponseToIncomeUseCase
    ) as T
}