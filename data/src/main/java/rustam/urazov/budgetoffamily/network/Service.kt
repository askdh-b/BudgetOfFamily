package rustam.urazov.budgetoffamily.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.auth.AuthBody
import rustam.urazov.budgetoffamily.network.models.auth.TokenResponse
import rustam.urazov.budgetoffamily.network.models.register.RegistrationBody

interface Service {

    @POST("auth")
    fun auth(@Body authBody: AuthBody): Call<TokenResponse>

    @POST("register")
    fun register(@Body registrationBody: RegistrationBody): Call<TokenResponse>
}