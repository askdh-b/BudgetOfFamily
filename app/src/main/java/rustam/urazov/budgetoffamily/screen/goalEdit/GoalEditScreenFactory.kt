package rustam.urazov.budgetoffamily.screen.goalEdit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.GoalRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.goal.EditGoalUseCase
import rustam.urazov.budgetoffamily.usecases.goal.GetGoalUseCase
import rustam.urazov.budgetoffamily.usecases.goal.MapResponseToGoalUseCase

class GoalEditScreenFactory(private val context: Context) : ViewModelProvider.Factory {

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

    private val goalRepository by lazy {
        GoalRepositoryImpl(networkService, dispatcher)
    }
    private val getGoalUseCase by lazy {
        GetGoalUseCase(goalRepository)
    }
    private val mapResponseToGoalUseCase by lazy {
        MapResponseToGoalUseCase(goalRepository)
    }
    private val editGoalUseCase by lazy {
        EditGoalUseCase(goalRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = GoalEditScreenViewModel(
        fragmentManager,
        getAccessTokenUseCase,
        getGoalUseCase,
        mapResponseToGoalUseCase,
        editGoalUseCase
    ) as T
}