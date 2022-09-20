package com.desafio.data.remote

import io.reactivex.Single
import java.io.IOException

interface FileDownloaderClient {

    fun execute(request: Request): Single<Response>

    data class Request(
        val method: String,
        val url: String,
    )

    data class Response(
        val statusCode: Int?,
        val data: ByteArray
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Response

            if (statusCode != other.statusCode) return false
            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = statusCode ?: 0
            result = 31 * result + data.contentHashCode()
            return result
        }
    }

    class Exception(val response: Response) : IOException()
}
