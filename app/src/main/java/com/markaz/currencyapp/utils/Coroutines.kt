package com.markaz.currencyapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    //UI contexts
    fun main(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main + handler).launch {
            work()
        }
    // I/O operations
    fun io(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO + handler).launch {
            work()
        }

    fun default(viewModel : ViewModel, work : suspend (() -> Unit)) {
        viewModel.viewModelScope.launch(Dispatchers.IO + handler) {
            work()
        }
    }
    // Uses heavy CPU computation
    fun default(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Default + handler).launch {
            work()
        }
    // No need to run on specific context
    fun unconfined(work : suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Unconfined + handler).launch {
            work()
        }
}