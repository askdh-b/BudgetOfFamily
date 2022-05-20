package rustam.urazov.budgetoffamily.storage

import android.content.Context
import rustam.urazov.budgetoffamily.storage.models.StorageAccessToken
import rustam.urazov.budgetoffamily.storage.models.StorageRefreshToken
import rustam.urazov.budgetoffamily.storage.models.StorageToken

class StorageServiceImpl(context: Context) : StorageService {

    companion object {
        const val SETTINGS = "settings"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    private val sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    override fun saveTokens(storageToken: StorageToken) =
        sharedPreferences.edit()
            .putString(ACCESS_TOKEN, storageToken.storageAccessToken.token)
            .putString(REFRESH_TOKEN, storageToken.storageRefreshToken.token)
            .apply()

    override fun getAccessToken(): StorageAccessToken = StorageAccessToken(
        token = sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
    )

    override fun getRefreshToken(): StorageRefreshToken = StorageRefreshToken(
        token = sharedPreferences.getString(REFRESH_TOKEN, "") ?: ""
    )
}