package rustam.urazov.budgetoffamily.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.*

interface NetworkService {

    @POST("auth")
    suspend fun auth(@Body authBody: AuthBody): TokenResponse

    @POST("register")
    suspend fun register(@Body registrationBody: RegistrationBody)

    @GET("income")
    suspend fun getIncomes(@Header("Authorization") token: String): List<IncomeResponse>

    @POST("income")
    suspend fun addIncome(@Header("Authorization") token: String, @Body incomeBody: IncomeBody)

    @GET("spending")
    suspend fun getSpendings(@Header("Authorization") token: String): List<SpendingResponse>

    @POST("spending")
    suspend fun addSpending(@Header("Authorization") token: String, @Body spendingBody: SpendingBody)
}