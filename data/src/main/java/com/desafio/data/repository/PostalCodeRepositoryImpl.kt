package com.desafio.data.repository

import androidx.annotation.VisibleForTesting
import com.desafio.data.remote.FileDownloaderClient
import com.desafio.domain.extensions.onCustomError
import com.desafio.domain.extensions.onCustomSuccess
import io.reactivex.Single
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class FileDownloaderImpl: FileDownloaderClient {

    private val okHttpClient = OkHttpClient()
    override fun execute(request: FileDownloaderClient.Request): Single<FileDownloaderClient.Response> {
        val okHttpRequest = getRequest(request)
        val requestCall = okHttpClient.newCall(okHttpRequest)
        return executeInternal(requestCall)
    }

    @VisibleForTesting
    fun executeInternal(requestCall: Call): Single<FileDownloaderClient.Response> {
        return Single.create<FileDownloaderClient.Response> {
            it.setCancellable { requestCall.cancel() }

            try {
                val response = requestCall.execute()
                when {
                    response.isSuccessful -> {
                        val responseData = createResponseData(response)
                        it.onCustomSuccess(responseData)
                    }

                    else -> it.onCustomError(
                        FileDownloaderClient.Exception(createResponseData(response))
                    )
                }
            } catch (ex: IOException) {
                when {
                    requestCall.isCanceled() -> return@create
                    else -> it.onCustomError(ex)
                }
            }
        }
    }

    private fun createResponseData(response: Response): FileDownloaderClient.Response {
        val body = response.body?.bytes() ?: byteArrayOf()
        val headers = mutableMapOf<String, String>()
        (0 until response.headers.size).forEach {
            val headerName = response.headers.name(it)
            val headerValue = response.headers.value(it)
            headers[headerName] = headerValue
        }

        return FileDownloaderClient.Response(response.code, body)
    }

    private fun getRequest(request: FileDownloaderClient.Request): Request {
        val builder = Request.Builder().url(request.url)
        addMethod(request, builder)
        return builder.build()
    }


    private fun addMethod(request: FileDownloaderClient.Request, requestBuilder: Request.Builder) {
        when (request.method) {
            "GET" -> requestBuilder.get()
            "DELETE" -> requestBuilder.delete()
            "HEAD" -> requestBuilder.head()
        }
    }
}
