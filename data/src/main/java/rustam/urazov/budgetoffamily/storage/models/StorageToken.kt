package rustam.urazov.budgetoffamily.storage.models

data class StorageToken(
    val storageAccessToken: StorageAccessToken,
    val storageRefreshToken: StorageRefreshToken
)