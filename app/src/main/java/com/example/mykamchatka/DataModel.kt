package com.example.mykamchatka

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykamchatka.data_classes.Tours

open class DataModel : ViewModel() {
    val cartItems: MutableLiveData<List<Tours>> by lazy {
        MutableLiveData<List<Tours>>()
    }
}