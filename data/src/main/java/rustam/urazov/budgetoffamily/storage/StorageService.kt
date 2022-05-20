package rustam.urazov.budgetoffamily.storage

import rustam.urazov.budgetoffamily.storage.models.StorageAccessToken
import rustam.urazov.budgetoffamily.storage.models.StorageRefreshToken
import rustam.urazov.budgetoffamily.storage.models.StorageToken

interface StorageService {

    fun saveTokens(storageToken: StorageToken)

    fun getAccessToken(): StorageAccessToken

    fun getRefreshToken(): StorageRefreshToken
}