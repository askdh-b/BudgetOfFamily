package rustam.urazov.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import rustam.urazov.data.network.models.auth.AuthBody
import rustam.urazov.data.network.models.auth.TokenNetwork

interface Service {

    @POST("auth")
    fun auth(@Body authBody: AuthBody): Call<TokenNetwork>
}