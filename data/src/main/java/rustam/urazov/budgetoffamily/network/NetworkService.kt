package rustam.urazov.budgetoffamily.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.AuthBody
import rustam.urazov.budgetoffamily.network.models.Income
import rustam.urazov.budgetoffamily.network.models.TokenResponse
import rustam.urazov.budgetoffamily.network.models.RegistrationBody

interface NetworkService {

    @POST("auth")
    suspend fun auth(@Body authBody: AuthBody): TokenResponse

    @POST("register")
    suspend fun register(@Body registrationBody: RegistrationBody)

    @GET("income")
    suspend fun getIncomes(@Header("Authorization") token: String): List<Income>

    @POST("income")
    suspend fun addIncome(@Header("Authorization") token: String, @Body income: Income)
}