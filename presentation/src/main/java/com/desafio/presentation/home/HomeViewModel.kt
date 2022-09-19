package com.desafio.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.desafio.domain.interactor.PostalCodeUseCase
import com.desafio.presentation.ui.base.BaseViewModel

class HomeViewModel(
    private val postalCodeUseCase: PostalCodeUseCase
) : BaseViewModel() {

    private val retry = MutableLiveData<Boolean>(false)
    val loading = MutableLiveData<Boolean>(true)

    val postalCodes = retry.switchMap {
        liveData {
            safeCoroutinesCall {
                postalCodeUseCase.getPostalCodes()
            }.coEither(
                error = {
                    loading.value = false
                    // Do error handling
                },
                success = {
                    loading.value = false
                    emit(it)
                }
            )
        }
    }
}