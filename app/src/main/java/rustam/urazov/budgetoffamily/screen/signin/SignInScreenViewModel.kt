package rustam.urazov.budgetoffamily.screen.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.domain.ResultWrapper
import rustam.urazov.domain.models.Token
import rustam.urazov.domain.models.UserAuthData
import rustam.urazov.domain.usecases.SaveTokenUseCase
import rustam.urazov.domain.usecases.UserAuthorizationUseCase

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