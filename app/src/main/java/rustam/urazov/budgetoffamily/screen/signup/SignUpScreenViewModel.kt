package rustam.urazov.budgetoffamily.screen.signup

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.NewUser
import rustam.urazov.budgetoffamily.models.token.Token
import rustam.urazov.budgetoffamily.models.user.UserAuthData
import rustam.urazov.budgetoffamily.network.models.auth.TokenResponse
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.auth.UserAuthorizationUseCase
import rustam.urazov.budgetoffamily.usecases.storage.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.register.UserRegistrationUseCase

class SignUpScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val registrationUseCase: UserRegistrationUseCase,
    private val authorizationUseCase: UserAuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    val token = MutableLiveData<String>()

    fun register(newUser: NewUser) {
        if (newUser.password == newUser.passwordAgain) {
            GlobalScope.launch {
                when (val result = registrationUseCase.execute(newUser)) {
                    is ResultWrapper.Error -> showErrorDialog(
                        fragmentManager,
                        result.error?.message.toString()
                    )
                    ResultWrapper.NetworkError -> showErrorDialog(
                        fragmentManager,
                        "Ошибка с сетью. Попробуйте позже."
                    )
                    is ResultWrapper.Success -> {
                        when (val result2 = authorizationUseCase.execute(
                            UserAuthData(
                                username = newUser.username,
                                password = newUser.password
                            )
                        )) {
                            is ResultWrapper.Error -> showErrorDialog(
                                fragmentManager,
                                result2.error?.message.toString()
                            )
                            ResultWrapper.NetworkError -> showErrorDialog(
                                fragmentManager,
                                "Ошибка с сетью. Попробуйте позже."
                            )
                            is ResultWrapper.Success -> {
                                token.postValue((result2.value as TokenResponse).accessToken)
                                saveTokenUseCase.execute(
                                    Token(
                                        accessToken = (result2.value as TokenResponse).accessToken,
                                        refreshToken = (result2.value as TokenResponse).refreshToken
                                    )
                                )
                            }
                        }
                    }
                }
            }
        } else showErrorDialog(fragmentManager, "Пожалуйста, заполните все поля")
    }
}