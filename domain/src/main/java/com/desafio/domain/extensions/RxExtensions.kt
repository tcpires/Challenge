package com.desafio.domain.extensions

import io.reactivex.*

fun <T: Any> SingleEmitter<T>.onCustomSuccess(result: T) {
    if (isDisposed.not()) onSuccess(result)
}

fun<T> SingleEmitter<T>.onCustomError(throwable: Throwable){
    if (isDisposed.not()) onError(throwable)
}