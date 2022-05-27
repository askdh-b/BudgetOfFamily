package rustam.urazov.budgetoffamily.screen.spendingsSourceAdd

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
import rustam.urazov.budgetoffamily.usecases.spendingsSource.AddSpendingsSourceUseCase

class SpendingsSourceAddScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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
    private val addSpendingsSourceUseCase by lazy {
        AddSpendingsSourceUseCase(spendingsSourceRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SpendingsSourceAddScreenViewModel(
            fragmentManager,
            getAccessTokenUseCase,
            addSpendingsSourceUseCase
        ) as T
}