package rustam.urazov.budgetoffamily.repositories.register

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.user.NewUser

interface UserRegistrationRepository {

    suspend fun register(newUser: NewUser): ResultWrapper<Any>
}