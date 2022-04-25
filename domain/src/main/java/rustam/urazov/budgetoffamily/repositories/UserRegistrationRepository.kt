package rustam.urazov.budgetoffamily.repositories

import rustam.urazov.budgetoffamily.ResultWrapper
import rustam.urazov.budgetoffamily.models.NewUser

interface UserRegistrationRepository {

    suspend fun register(newUser: NewUser): ResultWrapper<Any>
}