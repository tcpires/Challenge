package com.desafio.domain.util

sealed class Either<out L, out R> {

    data class Error<out L>(val error: L) : Either<L, Nothing>()

    data class Success<out R>(val success: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error<L>

    suspend fun coEither(error: suspend (L) -> Unit, success: suspend (R) -> Unit): Any =
        when (this) {
            is Error -> error(this.error)
            is Success -> success(this.success)
        }
}

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

fun <L> L.toError(): Either.Error<L> {
    return Either.Error(this)
}