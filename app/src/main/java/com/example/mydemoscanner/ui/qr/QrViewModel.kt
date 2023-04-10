package com.example.mydemoscanner.ui.qr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QrViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is QR Fragment"
    }
    val text: LiveData<String> = _text
}