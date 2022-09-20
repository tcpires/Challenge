package com.desafio.presentation.home

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.desafio.domain.interactor.PostalCodeUseCase
import com.desafio.presentation.ui.base.BaseViewModel

class HomeViewModel(
    private val postalCodeUseCase: PostalCodeUseCase
) : BaseViewModel() {

    val postalCodes = MutableLiveData<ByteArray>()
    val loading = MutableLiveData<Boolean>(false)

    fun getPostalCodes() {
        loading.value = true
        subscribeSingle(
            observable = postalCodeUseCase.downloadPostalCodesCvs(),
            success = {
                loading.value = false
                postalCodes.value = it.data},
            error = {
                loading.value = false
            }
        )
    }
}
