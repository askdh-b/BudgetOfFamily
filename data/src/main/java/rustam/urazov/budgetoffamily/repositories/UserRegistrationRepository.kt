package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.NewUser
import rustam.urazov.budgetoffamily.network.NetworkService
import rustam.urazov.budgetoffamily.network.models.RegistrationBody
import rustam.urazov.budgetoffamily.network.safeCall

class UserRegistrationRepositoryImpl(
    private val networkService: NetworkService,
    private val dispatcher: CoroutineDispatcher
) : UserRegistrationRepository {

    override suspend fun register(newUser: NewUser): ResultWrapper<Any> {

        val registrationBody =
            RegistrationBody(
                newUser.firstName,
                newUser.lastName,
                newUser.username,
                newUser.password
            )

        return safeCall(dispatcher, call = {
            networkService.register(registrationBody)
        })
    }
}