package com.example.fetchcodingexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import java.net.URL
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val LOGTAG = "ViewModel"

class ViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

    fun getItems(): String {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(LOGTAG, "retrieving the items")
                val response = URL(url).readText()
                val hi = 1
            } catch (e: Exception) {
                Log.e(LOGTAG, e.toString())
            }
        }
        return "hello"
    }
}

private fun CoroutineScope.launch(context: CoroutineDispatcher) {
    TODO("Not yet implemented")
}
