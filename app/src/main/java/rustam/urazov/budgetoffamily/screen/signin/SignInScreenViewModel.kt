package rustam.urazov.budgetoffamily.screen.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.models.user.UserAuthData
import rustam.urazov.budgetoffamily.usecases.storage.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.auth.UserAuthorizationUseCase

class SignInScreenViewModel(
    private val userAuthorizationUseCase: UserAuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    fun authorize(userAuthData: UserAuthData) {
        GlobalScope.launch {
            when (val result = userAuthorizationUseCase.execute(userAuthData)) {
                is ResultWrapper.Error -> {}
                ResultWrapper.NetworkError -> {}
                is ResultWrapper.Success -> { saveTokenUseCase.execute(Token(result.toString())) }
            }
        }
    }
}