package com.example.mykamchatka

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykamchatka.data_classes.Tours

open class DataModel : ViewModel() {
    val cartItems: MutableLiveData<List<Tours>> by lazy {
        MutableLiveData<List<Tours>>()
    }

    val imageUris: MutableLiveData<List<Uri?>> by lazy {
        MutableLiveData<List<Uri?>>(mutableListOf())
    }
}