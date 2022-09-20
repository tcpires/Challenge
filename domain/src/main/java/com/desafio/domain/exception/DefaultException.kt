package com.desafio.domain.exception

import java.io.IOException

open class DefaultException(
    val code: String = "",
    override val message: String = "Algo de ruim..."
) : IOException(){

    fun code() = ExceptionCode.values()
        .find { it.code ==  this.code }
        ?: ExceptionCode.UNKNOW

}