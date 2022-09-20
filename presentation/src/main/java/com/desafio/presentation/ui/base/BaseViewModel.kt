package com.desafio.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.desafio.domain.exception.DefaultException
import com.desafio.domain.util.Either
import com.desafio.domain.util.toError
import com.desafio.domain.util.toSuccess
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    private val initiated = AtomicBoolean()

    fun initOnce(onInit: () -> Unit) {
        if (initiated.compareAndSet(false, true)) {
            onInit.invoke()
        }
    }

    protected suspend fun <T> safeCoroutinesCall(
        retrofitCall: suspend () -> T
    ): Either<DefaultException, T> {
        return try {
            retrofitCall().toSuccess()
        } catch (error: Exception) {
            return when (error) {
                is DefaultException -> error.toError()

                else -> DefaultException().toError()
            }
        }
    }

    protected fun <T> subscribeSingle(
        observable: Single<T>,
        success: ((T) -> Unit)? = null,
        error: ((DefaultException) -> Unit)? = null
    ): Disposable {
        val disposable = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    success,
                    { subscribeError(it, error) }
                )

        compositeDisposable.add(disposable)

        return disposable
    }

    protected fun subscribeError(
        throwable: Throwable,
        error: ((DefaultException) -> Unit)? = null
    ) {
        error?.invoke(DefaultException())
    }
}