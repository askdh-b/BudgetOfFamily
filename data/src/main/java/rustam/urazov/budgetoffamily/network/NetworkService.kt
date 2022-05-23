package rustam.urazov.budgetoffamily.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import rustam.urazov.budgetoffamily.network.models.*

interface NetworkService {

    // User
    @POST("auth")
    suspend fun auth(@Body authBody: AuthBody): TokenResponse

    @POST("register")
    suspend fun register(@Body registrationBody: RegistrationBody)

    // Incomes
    @GET("income")
    suspend fun getIncomes(@Header("Authorization") token: String): List<IncomeResponse>

    @POST("income")
    suspend fun addIncome(@Header("Authorization") token: String, @Body incomeBody: IncomeBody)

    // Spendings
    @GET("spending")
    suspend fun getSpendings(@Header("Authorization") token: String): List<SpendingResponse>

    @POST("spending")
    suspend fun addSpending(@Header("Authorization") token: String, @Body spendingBody: SpendingBody)

    // Incomes Sources
    @GET("incomesSource")
    suspend fun getIncomesSources(@Header("Authorization") token: String): List<IncomesSourceResponse>

    // Spendings Sources
    @GET("spendingsSource")
    suspend fun getSpendingsSources(@Header("Authorization") token: String): List<SpendingsSourceResponse>

    // Goals
    @GET("goal")
    suspend fun getGoals(@Header("Authorization") token: String): List<GoalResponse>
}