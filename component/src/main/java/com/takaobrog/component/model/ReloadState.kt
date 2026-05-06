package com.takaobrog.component.model

sealed class ReloadState {
    object Idle : ReloadState()
    object Reloading : ReloadState()
    data class Error(val error: ErrorState) : ReloadState()
}