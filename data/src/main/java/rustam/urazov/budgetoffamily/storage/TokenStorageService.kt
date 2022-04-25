package rustam.urazov.budgetoffamily.storage

import rustam.urazov.budgetoffamily.storage.models.AccessToken

interface TokenStorageService {

    fun save(accessToken: AccessToken)

    fun get(): AccessToken
}