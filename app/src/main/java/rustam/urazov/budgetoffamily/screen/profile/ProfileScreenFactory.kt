package rustam.urazov.budgetoffamily.screen.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.*
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
        IncomeRepositoryImpl(networkService, dispatcher)
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
        SpendingRepositoryImpl(networkService, dispatcher)
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

    private val incomesSourceRepository by lazy {
        IncomesSourceRepositoryImpl(networkService, dispatcher)
    }
    private val getIncomesSourceUseCase by lazy {
        GetIncomesSourcesUseCase(incomesSourceRepository)
    }
    private val mapResponseToIncomesSourceUseCase by lazy {
        MapResponseToIncomesSourceUseCase(incomesSourceRepository)
    }

    private val getIncomesSourcesSumUseCase by lazy {
        GetIncomesSourcesSumUseCase()
    }

    private val spendingsSourceRepository by lazy {
        SpendingsSourceRepositoryImpl(networkService, dispatcher)
    }
    private val getSpendingsSourceUseCase by lazy {
        GetSpendingsSourceUseCase(spendingsSourceRepository)
    }
    private val mapResponseToSpendingsSourceUseCase by lazy {
        MapResponseToSpendingsSourceUseCase(spendingsSourceRepository)
    }

    private val getSpendingsSourcesSumUseCase by lazy {
        GetSpendingsSourceSumUseCase()
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
        getBalanceUseCase,
        getIncomesSourceUseCase,
        mapResponseToIncomesSourceUseCase,
        getIncomesSourcesSumUseCase,
        getSpendingsSourceUseCase,
        mapResponseToSpendingsSourceUseCase,
        getSpendingsSourcesSumUseCase) as T
}