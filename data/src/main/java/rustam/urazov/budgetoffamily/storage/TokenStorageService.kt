package rustam.urazov.budgetoffamily.storage

import rustam.urazov.budgetoffamily.storage.models.TokenStorage

interface TokenStorageService {

    fun save(tokenStorage: TokenStorage)

    fun get(): TokenStorage
}