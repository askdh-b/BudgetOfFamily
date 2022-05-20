package rustam.urazov.budgetoffamily.screen.signin

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.models.user.UserAuthData
import rustam.urazov.budgetoffamily.network.models.auth.TokenResponse
import rustam.urazov.budgetoffamily.screen.ErrorDialog
import rustam.urazov.budgetoffamily.usecases.storage.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.auth.UserAuthorizationUseCase

class SignInScreenViewModel(
    private val userAuthorizationUseCase: UserAuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val fragmentManager: FragmentManager
) : ViewModel() {

    val token = MutableLiveData<String>()

    fun authorize(userAuthData: UserAuthData) = GlobalScope.launch(Dispatchers.IO) {
        when (val result = userAuthorizationUseCase.execute(userAuthData)) {
            is ResultWrapper.Error -> showErrorDialog(fragmentManager, result.error?.message.toString())
            ResultWrapper.NetworkError -> showErrorDialog(
                fragmentManager,
                "Ошибка с сетью. Попробуйте позже."
            )
            is ResultWrapper.Success -> {
                token.postValue((result.value as TokenResponse).accessToken)
                saveTokenUseCase.execute(
                    Token(
                        accessToken = (result.value as TokenResponse).accessToken,
                        refreshToken = (result.value as TokenResponse).refreshToken
                    )
                )
            }
        }
    }
}

fun showErrorDialog(fragmentManager: FragmentManager, message: String) {
    val errorDialog = ErrorDialog(message)
    errorDialog.show(fragmentManager, "")
}