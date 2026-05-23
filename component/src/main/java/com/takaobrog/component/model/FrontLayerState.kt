package com.takaobrog.component.model

sealed class FrontLayerState {
    object Idle : FrontLayerState()
    object Loading : FrontLayerState()
    data class Confirm(val title: String) : FrontLayerState()
    data class Error(val error: ErrorState) : FrontLayerState()
}