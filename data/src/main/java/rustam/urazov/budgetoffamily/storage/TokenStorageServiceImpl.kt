package rustam.urazov.budgetoffamily.storage

import android.content.Context
import rustam.urazov.budgetoffamily.storage.models.AccessToken

class TokenStorageServiceImpl(private val context: Context) : TokenStorageService {

    companion object {

        const val SETTINGS = "settings"
        const val ACCESS_TOKEN = "access_token"
    }

    private val sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    override fun save(accessToken: AccessToken) {

        sharedPreferences.edit().putString(ACCESS_TOKEN, accessToken.token).apply()
    }

    override fun get(): AccessToken {

        return AccessToken(sharedPreferences.getString(ACCESS_TOKEN, "") ?: "")
    }
}