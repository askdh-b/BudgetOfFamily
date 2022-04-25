package rustam.urazov.budgetoffamily.screen.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.NewUser
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.usecases.storage.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.register.UserRegistrationUseCase

class SignUpScreenViewModel(
    private val registrationUseCase: UserRegistrationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    fun register(newUser: NewUser) {
        GlobalScope.launch {
            when (val result = registrationUseCase.execute(newUser)) {
                is ResultWrapper.Error -> {}
                ResultWrapper.NetworkError -> {}
                is ResultWrapper.Success -> { saveTokenUseCase.execute(Token(result.toString())) }
            }
        }
    }
}