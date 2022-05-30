package rustam.urazov.budgetoffamily.screen.completedGoals

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.goal.*

class CompletedGoalsScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getGoalsUseCase: GetGoalsUseCase,
    private val mapResponseToGoalUseCase: MapResponseToGoalUseCase,
    private val getCompletedGoalsUseCase: GetCompletedGoalsUseCase,
) : ViewModel() {

    val completedGoals = MutableLiveData<List<GoalData>>()

    fun getGoals() = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getGoalsUseCase.execute(getAccessToken())) {
            is ResultWrapper.Error -> showErrorDialog(
                fragmentManager,
                result.error?.message.toString()
            )
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val goals = mapResponseToGoalUseCase.execute(result.value as List<*>)
                val coGs = getCompletedGoalsUseCase.execute(goals)
                completedGoals.postValue(coGs)
            }
        }
    }

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}