package rustam.urazov.budgetoffamily.network

import retrofit2.http.*
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
    suspend fun addSpending(
        @Header("Authorization") token: String,
        @Body spendingBody: SpendingBody
    )

    // Incomes Sources
    @GET("incomesSource")
    suspend fun getIncomesSources(@Header("Authorization") token: String): List<IncomesSourceResponse>

    @GET("incomesSource/{id}")
    suspend fun getIncomesSource(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): IncomesSourceResponse

    @POST("incomesSource")
    suspend fun addIncomesSource(
        @Header("Authorization") token: String,
        @Body incomesSourceBody: IncomesSourceBody
    )

    @PUT("incomesSource/{id}")
    suspend fun editIncomesSource(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body incomesSourceBody: IncomesSourceBody
    )

    @DELETE("incomesSource/{id}")
    suspend fun deleteIncomesSource(@Header("Authorization") token: String, @Path("id") id: Int)

    // Spendings Sources
    @GET("spendingsSource")
    suspend fun getSpendingsSources(@Header("Authorization") token: String): List<SpendingsSourceResponse>

    @GET("spendingsSource/{id}")
    suspend fun getSpendingsSource(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): SpendingsSourceResponse

    @POST("spendingsSource")
    suspend fun addSpendingsSource(
        @Header("Authorization") token: String,
        @Body spendingsSourceBody: SpendingsSourceBody
    )

    @PUT("spendingsSource/{id}")
    suspend fun editSpendingsSource(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body spendingsSourceBody: SpendingsSourceBody
    )

    @DELETE("spendingsSource/{id}")
    suspend fun deleteSpendingsSource(@Header("Authorization") token: String, @Path("id") id: Int)

    // Goals
    @GET("goal")
    suspend fun getGoals(@Header("Authorization") token: String): List<GoalResponse>

    @GET("goal/{id}")
    suspend fun getGoal(@Header("Authorization") token: String, @Path("id") id: Int): GoalResponse

    @POST("goal")
    suspend fun addGoal(@Header("Authorization") token: String, @Body goalBody: GoalBody)

    @PUT("goal/{id}")
    suspend fun editGoal(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body goalBodyForEdit: GoalBodyForEdit
    )

    @DELETE("goal/{id}")
    suspend fun deleteGoal(@Header("Authorization") token: String, @Path("id") id: Int)

    // Invitations
    @GET("invitation")
    suspend fun getInvitations(@Header("Authorization") token: String): List<InvitationResponse>

    @POST("invitation")
    suspend fun sendInvitation(
        @Header("Authorization") token: String,
        @Body invitationBody: InvitationBody
    )

    @POST("invitation/{id}")
    suspend fun acceptInvitation(@Header("Authorization") token: String, @Path("id") id: Int)

    @DELETE("invitation/{id}")
    suspend fun rejectInvitation(@Header("Authorization") token: String, @Path("id") id: Int)

    // Search
    @GET("search")
    suspend fun search(
        @Header("authorization") token: String,
        @Query("q") q: String
    ): List<UserResponse>

    // Family
    @POST("leave")
    suspend fun leave(@Header("Authorization") token: String)
}