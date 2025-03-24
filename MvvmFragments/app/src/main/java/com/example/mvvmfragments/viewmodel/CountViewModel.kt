package com.example.mvvmfragments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel(){

    private var count = MutableLiveData<Int>()
    init {
        count.value = 0
    }

    fun getCountLiveData() : LiveData<Int> {
        return count
    }

    fun increment() {
        count.value = count.value!! + 1
    }

    fun decrement() {
        count.value = count.value!! - 1
    }
}