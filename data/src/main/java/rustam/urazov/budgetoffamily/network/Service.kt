package rustam.urazov.budgetoffamily.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.auth.AuthBody
import rustam.urazov.budgetoffamily.network.models.auth.TokenNetwork
import rustam.urazov.budgetoffamily.network.models.register.RegistrationBody

interface Service {

    @POST("auth")
    fun auth(@Body authBody: AuthBody): Call<TokenNetwork>

    @POST("register")
    fun register(@Body registrationBody: RegistrationBody): Call<TokenNetwork>
}