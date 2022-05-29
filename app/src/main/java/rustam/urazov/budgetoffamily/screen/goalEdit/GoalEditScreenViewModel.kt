package rustam.urazov.budgetoffamily.screen.goalEdit

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.GoalData
import rustam.urazov.budgetoffamily.models.GoalForEdit
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.GetAccessTokenUseCase
import rustam.urazov.budgetoffamily.usecases.goal.EditGoalUseCase
import rustam.urazov.budgetoffamily.usecases.goal.GetGoalUseCase
import rustam.urazov.budgetoffamily.usecases.goal.MapResponseToGoalUseCase

class GoalEditScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getGoalUseCase: GetGoalUseCase,
    private val mapResponseToGoalUseCase: MapResponseToGoalUseCase,
    private val editGoalUseCase: EditGoalUseCase
) : ViewModel() {

    val goal = MutableLiveData<GoalData>()
    val success = MutableLiveData<Boolean>()

    fun getGoal(id: Int) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = getGoalUseCase.execute(getAccessToken(), id)) {
            is ResultWrapper.Error -> showErrorDialog(fragmentManager, result.error?.message.toString())
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                val gs = mapResponseToGoalUseCase.execute(listOf(result.value) as List<*>)
                goal.postValue(gs[0])
            }
        }
    }

    fun editGoal(id: Int, goalForEdit: GoalForEdit) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = editGoalUseCase.execute(getAccessToken(), id, goalForEdit)) {
            is ResultWrapper.Error -> showErrorDialog(fragmentManager, result.error?.message.toString())
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                success.postValue(true)
            }
        }
    }

    fun showError() = showErrorDialog(
        fragmentManager,
        "Введите корректные данные"
    )

    private suspend fun getAccessToken() = getAccessTokenUseCase.execute()
}