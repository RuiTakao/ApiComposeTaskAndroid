package com.takaobrog.apicomposetask.util

sealed class ErrorState {
    data class SystemError(val message: String?) : ErrorState()
    object NetworkError : ErrorState()
}