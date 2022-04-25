package rustam.urazov.budgetoffamily.repositories

import kotlinx.coroutines.CoroutineDispatcher
import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.NewUser
import rustam.urazov.budgetoffamily.network.Service
import rustam.urazov.budgetoffamily.network.models.register.RegistrationBody
import rustam.urazov.budgetoffamily.network.safeCall
import rustam.urazov.budgetoffamily.repositories.register.UserRegistrationRepository

class UserRegistrationRepositoryImpl(

    private val service: Service,
    private val dispatcher: CoroutineDispatcher
) : UserRegistrationRepository {

    override suspend fun register(newUser: NewUser): ResultWrapper<Any> {

        val registrationBody =
            RegistrationBody(
                newUser.firstName,
                newUser.lastName,
                newUser.email,
                newUser.password
            )

        return safeCall(dispatcher, call = {
            service.register(registrationBody)
        })
    }
}