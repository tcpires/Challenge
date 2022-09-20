package com.desafio.presentation.ui.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(
    owner: LifecycleOwner,
    result: (T) -> Unit
){
    this.observe(
        owner,
        Observer {
            it?.let { result }
        }
    )
}