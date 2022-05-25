package rustam.urazov.budgetoffamily.storage.models

data class StorageToken(
    val userId: Int,
    val storageAccessToken: StorageAccessToken,
    val storageRefreshToken: StorageRefreshToken
)