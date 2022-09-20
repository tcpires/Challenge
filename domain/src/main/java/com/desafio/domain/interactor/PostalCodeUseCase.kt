package com.desafio.domain.interactor

import com.desafio.data.remote.FileDownloaderClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PostalCodeUseCase(
    private val postalCodeRepository: FileDownloaderClient
) {
    private companion object {
        const val URL = "https://github.com/centraldedados/codigos_postais/raw/e49281814d07ee3feda27b1f1954ac263ca42b21/data/codigos_postais.csv"
    }

    fun downloadPostalCodesCvs(): Single<FileDownloaderClient.Response> {
        val requestData = FileDownloaderClient.Request(
            url = URL,
            method = "GET"
        )

        return postalCodeRepository.execute(requestData).subscribeOn(Schedulers.io())
    }
}