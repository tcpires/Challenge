package com.desafio.domain.interactor

import com.desafio.domain.model.DataPackage
import com.desafio.domain.repository.PostalCodeRepository

class PostalCodeUseCase(
    private val postalCodeRepository: PostalCodeRepository
) {

    suspend fun getPostalCodes(): DataPackage.Response{
        return postalCodeRepository.getPostalCodes()
    }
}