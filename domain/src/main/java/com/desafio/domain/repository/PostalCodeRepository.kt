package com.desafio.domain.repository

import com.desafio.domain.model.DataPackage

interface PostalCodeRepository {

    suspend fun getPostalCodes() : DataPackage.Response
}