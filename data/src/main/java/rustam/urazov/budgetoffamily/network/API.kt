package rustam.urazov.budgetoffamily.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    companion object {
        const val BASE_URL = "https://budgetoffamilybackend.herokuapp.com/"
        val mInstance = API()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val networkService: NetworkService = retrofit.create(NetworkService::class.java)
}