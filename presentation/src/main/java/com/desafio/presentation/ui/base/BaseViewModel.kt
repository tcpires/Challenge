package com.desafio.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.desafio.domain.exception.DefaultException
import com.desafio.domain.util.Either
import com.desafio.domain.util.toError
import com.desafio.domain.util.toSuccess
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    private val initiated = AtomicBoolean()

    fun initOnce(onInit: () -> Unit) {
        if (initiated.compareAndSet(false, true)) {
            onInit.invoke()
        }
    }

    protected suspend fun <T>safeCoroutinesCall(
        retrofitCall: suspend  () -> T
    ): Either<DefaultException, T>{
        return try {
            retrofitCall().toSuccess()
        } catch (error: Exception){
            return when(error){
                is DefaultException -> error.toError()

                else -> DefaultException().toError()
            }
        }
    }
}