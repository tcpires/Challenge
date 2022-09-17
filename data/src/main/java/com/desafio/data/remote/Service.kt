package com.desafio.data.remote

import com.desafio.data.remote.dto.DataPackageDto
import retrofit2.http.GET

interface Service {

    @GET("master/datapackage.json")
    fun getPostalCodes():DataPackageDto.Response
}