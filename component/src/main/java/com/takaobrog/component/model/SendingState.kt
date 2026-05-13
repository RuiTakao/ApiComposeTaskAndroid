package com.takaobrog.component.model

sealed class SendingState {
    object Idle : SendingState()
    object Sending : SendingState()
    data class Error(val error: ErrorState) : SendingState()
}