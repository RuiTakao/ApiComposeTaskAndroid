package com.takaobrog.component.model

sealed class DialogState {
    object Idle : DialogState()
    data class Confirm(val title: String) : DialogState()
    data class Error(val error: ErrorState) : DialogState()
}