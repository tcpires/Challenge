package com.desafio.data.repository

import com.desafio.data.remote.Service
import com.desafio.domain.util.CoroutineContextProvider
import com.desafio.domain.model.DataPackage
import com.desafio.domain.repository.PostalCodeRepository
import kotlinx.coroutines.withContext

class PostalCodeRepositoryImpl(
    private val service: Service,
    private val coroutineContextProvider: CoroutineContextProvider
) : PostalCodeRepository {

    override suspend fun getPostalCodes(): DataPackage.Response {
        return withContext(coroutineContextProvider.IO) {
            service.getPostalCodes().toDataPackage()
        }
    }
}