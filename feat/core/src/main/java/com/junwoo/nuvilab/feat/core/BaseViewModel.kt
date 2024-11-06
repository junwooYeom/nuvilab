package com.junwoo.nuvilab.feat.core

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

interface UIAction

interface UIState {
    val isLoading: Boolean
}

interface UIEffect

open class BaseViewModel<A: UIAction, S: UIState, E: UIEffect>(
    initialState: S
): ViewModel(
){
    var uiState by mutableStateOf(initialState)
        protected set

    private val _action: MutableSharedFlow<A> = MutableSharedFlow()

    @Suppress("UNUSED")
    private val action = _action.onEach {
        handleAction(it)
    }.shareIn(viewModelScope, started = SharingStarted.WhileSubscribed(5000))
        .launchIn(viewModelScope)

    var effect by mutableStateOf<E?>(null)
        protected set

    open suspend fun handleAction(action: A) {}

    fun setState(block: suspend S.() -> S) = viewModelScope.launch {
        val nextState = block(uiState)
        uiState = nextState
    }

    fun setEffect(block: suspend () -> E) = viewModelScope.launch {
        effect = block()
        delay(100)
        effect = null
    }

    fun setAction(action: A) = viewModelScope.launch {
        _action.emit(action)
    }
}