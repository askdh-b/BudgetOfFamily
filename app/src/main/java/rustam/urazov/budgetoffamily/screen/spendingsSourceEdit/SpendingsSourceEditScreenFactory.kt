package rustam.urazov.budgetoffamily.screen.spendingsSourceEdit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.SpendingsSourceRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.EditSpendingsSourceUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.GetSpendingsSourceUseCase
import rustam.urazov.budgetoffamily.usecases.spendingsSource.MapResponseToSpendingsSourceUseCase

class SpendingsSourceEditScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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

    private val spendingsSourceRepository by lazy {
        SpendingsSourceRepositoryImpl(networkService, dispatcher)
    }
    private val getSpendingsSourceUseCase by lazy {
        GetSpendingsSourceUseCase(spendingsSourceRepository)
    }
    private val mapResponseToSpendingsSourceUseCase by lazy {
        MapResponseToSpendingsSourceUseCase(spendingsSourceRepository)
    }
    private val editSpendingsSourceUseCase by lazy {
        EditSpendingsSourceUseCase(spendingsSourceRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SpendingsSourceEditScreenViewModel(
            fragmentManager,
            getAccessTokenUseCase,
            getSpendingsSourceUseCase,
            mapResponseToSpendingsSourceUseCase,
            editSpendingsSourceUseCase
        ) as T
}