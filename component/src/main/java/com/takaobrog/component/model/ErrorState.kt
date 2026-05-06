package com.takaobrog.component.model

sealed class ErrorState {
    data class SystemError(val message: String?) : ErrorState()
    object NetworkError : ErrorState()
}