package rustam.urazov.budgetoffamily.storage

import android.content.Context
import rustam.urazov.budgetoffamily.storage.models.TokenStorage

class TokenStorageServiceImpl(private val context: Context) : TokenStorageService {

    companion object {

        const val SETTINGS = "settings"
        const val ACCESS_TOKEN = "access_token"
    }

    private val sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    override fun save(tokenStorage: TokenStorage) {

        sharedPreferences.edit().putString(ACCESS_TOKEN, tokenStorage.token).apply()
    }

    override fun get(): TokenStorage {

        return TokenStorage(sharedPreferences.getString(ACCESS_TOKEN, "") ?: "")
    }
}