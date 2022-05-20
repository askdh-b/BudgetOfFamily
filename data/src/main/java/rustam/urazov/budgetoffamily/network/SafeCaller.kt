package rustam.urazov.budgetoffamily.network

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import rustam.urazov.budgetoffamily.models.error.ErrorResponse
import rustam.urazov.budgetoffamily.ResultWrapper
import java.io.BufferedInputStream
import java.io.IOException

suspend fun <T> safeCall(dispatcher: CoroutineDispatcher, call: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(call.invoke())
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    t.printStackTrace()
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = t.code()
                    val errorResponse = convertErrorBody(t)
                    ResultWrapper.Error(code, ErrorResponse(errorResponse.orEmpty()))
                }
                else -> {
                    Log.e("er", t.message.toString())
                    ResultWrapper.Error(error = ErrorResponse("Нет данных. Попробуйте позже"))
                }
            }
        }
    }
}

private fun convertErrorBody(httpException: HttpException): String? {
    return try {
        httpException.response()?.errorBody()?.source()?.let { it ->
            val inputStream = BufferedInputStream(it.inputStream())
            val contents = ByteArray(1024)

            var bytesRead: Int
            var strFileContents: String? = null
            while (inputStream.read(contents).also { bytesRead = it } != -1) {
                strFileContents += String(contents, 0, bytesRead)
            }
            return strFileContents?.substring(4)
        }
    } catch (exception: Exception) {
        null
    }
}