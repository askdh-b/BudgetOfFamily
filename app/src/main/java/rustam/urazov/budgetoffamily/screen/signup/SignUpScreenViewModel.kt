package rustam.urazov.budgetoffamily.screen.signup

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.NewUser
import rustam.urazov.budgetoffamily.models.Token
import rustam.urazov.budgetoffamily.models.UserAuthData
import rustam.urazov.budgetoffamily.network.models.TokenResponse
import rustam.urazov.budgetoffamily.screen.showErrorDialog
import rustam.urazov.budgetoffamily.usecases.UserAuthorizationUseCase
import rustam.urazov.budgetoffamily.usecases.SaveTokenUseCase
import rustam.urazov.budgetoffamily.usecases.UserRegistrationUseCase

class SignUpScreenViewModel(
    private val fragmentManager: FragmentManager,
    private val registrationUseCase: UserRegistrationUseCase,
    private val authorizationUseCase: UserAuthorizationUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    val token = MutableLiveData<String>()

    fun register(newUser: NewUser) {
        if (newUser.firstName.length >= 2 && newUser.lastName.length >= 2 && newUser.username.length >= 5 && newUser.password.length >= 6)
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
                                            userId = (result2.value as TokenResponse).userId,
                                            accessToken = (result2.value as TokenResponse).accessToken,
                                            refreshToken = (result2.value as TokenResponse).refreshToken
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            } else showErrorDialog(fragmentManager, "Пожалуйста, заполните поля корректно")
        else showErrorDialog(fragmentManager, "Пожалуйста заполните все поля")
    }

    fun showError() = showErrorDialog(
        fragmentManager,
        "Пожалуйста, заполните поля корректно"
    )
}