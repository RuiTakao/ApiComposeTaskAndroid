package com.takaobrog.apicomposetask.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    fun test() {
        Log.d("DEBUG", "test")
    }
}