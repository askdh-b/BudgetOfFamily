package rustam.urazov.budgetoffamily.network

import retrofit2.http.Body
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.auth.AuthBody
import rustam.urazov.budgetoffamily.network.models.auth.TokenResponse
import rustam.urazov.budgetoffamily.network.models.register.RegistrationBody

interface NetworkService {

    @POST("auth")
    suspend fun auth(@Body authBody: AuthBody): TokenResponse

    @POST("register")
    suspend fun register(@Body registrationBody: RegistrationBody)
}