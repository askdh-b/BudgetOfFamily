package rustam.urazov.data.storage

import rustam.urazov.data.storage.models.TokenStorage

interface TokenStorageService {

    fun save(tokenStorage: TokenStorage)

    fun get(): TokenStorage
}