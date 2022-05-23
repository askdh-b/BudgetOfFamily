package rustam.urazov.budgetoffamily.screen.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.IncomesRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.SpendingsRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.*

class ProfileScreenFactory(context: Context) : ViewModelProvider.Factory {

    private val dispatcher = Dispatchers.IO
    private val networkService = API.mInstance.networkService
    private val fragmentManager = (context as MainActivity).supportFragmentManager

    private val storageService by lazy {
        StorageServiceImpl(context)
    }
    private val tokenRepository by lazy {
        TokenRepositoryImpl(storageService)
    }
    private val getAccessTokenUseCase by lazy {
        GetAccessTokenUseCase(tokenRepository)
    }

    private val incomesRepository by lazy {
        IncomesRepositoryImpl(networkService, dispatcher)
    }
    private val getIncomesUseCase by lazy {
        GetIncomesUseCase(incomesRepository)
    }
    private val mapResponseToIncomeUseCase by lazy {
        MapResponseToIncomeUseCase(incomesRepository)
    }

    private val getIncomesSumUseCase by lazy {
        GetIncomesSumUseCase()
    }

    private val spendingsRepository by lazy {
        SpendingsRepositoryImpl(networkService, dispatcher)
    }
    private val getSpendingUseCase by lazy {
        GetSpendingsUseCase(spendingsRepository)
    }
    private val mapResponseToSpendingUseCase by lazy {
        MapResponseToSpendingUseCase(spendingsRepository)
    }

    private val getSpendingsSumUseCase by lazy {
        GetSpendingsSumUseCase()
    }

    private val getBalanceUseCase by lazy {
        GetBalanceUseCase()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ProfileScreenViewModel(fragmentManager,
        getAccessTokenUseCase,
        getIncomesUseCase,
        mapResponseToIncomeUseCase,
        getIncomesSumUseCase,
        getSpendingUseCase,
        mapResponseToSpendingUseCase,
        getSpendingsSumUseCase,
        getBalanceUseCase) as T
}