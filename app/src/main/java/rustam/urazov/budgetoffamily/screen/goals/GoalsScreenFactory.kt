package rustam.urazov.budgetoffamily.screen.goals

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import rustam.urazov.budgetoffamily.activity.MainActivity
import rustam.urazov.budgetoffamily.network.API
import rustam.urazov.budgetoffamily.repositories.GoalRepositoryImpl
import rustam.urazov.budgetoffamily.repositories.TokenRepositoryImpl
import rustam.urazov.budgetoffamily.storage.StorageServiceImpl
import rustam.urazov.budgetoffamily.usecases.*
import rustam.urazov.budgetoffamily.usecases.goal.*

class GoalsScreenFactory(context: Context) : ViewModelProvider.Factory {

    private val fragmentManager = (context as MainActivity).supportFragmentManager
    private val networkService = API.mInstance.networkService
    private val dispatcher = Dispatchers.IO

    private val storageService by lazy {
        StorageServiceImpl(context)
    }
    private val tokenRepository by lazy {
        TokenRepositoryImpl(storageService)
    }
    private val getAccessTokenUseContext by lazy {
        GetAccessTokenUseCase(tokenRepository)
    }

    private val goalRepository by lazy {
        GoalRepositoryImpl(networkService, dispatcher)
    }
    private val getGoalsUseCase by lazy {
        GetGoalsUseCase(goalRepository)
    }
    private val mapResponseToGoalUseCase by lazy {
        MapResponseToGoalUseCase(goalRepository)
    }
    private val deleteGoalUseCase by lazy {
        DeleteGoalUseCase(goalRepository)
    }

    private val getCurrentGoalsUseCase by lazy {
        GetCurrentGoalsUseCase()
    }

    private val getCompletedGoalsUseCase by lazy {
        GetCompletedGoalsUseCase()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = GoalsScreenViewModel(
        fragmentManager,
        getAccessTokenUseContext,
        getGoalsUseCase,
        mapResponseToGoalUseCase,
        getCurrentGoalsUseCase,
        getCompletedGoalsUseCase,
        deleteGoalUseCase
    ) as T
}