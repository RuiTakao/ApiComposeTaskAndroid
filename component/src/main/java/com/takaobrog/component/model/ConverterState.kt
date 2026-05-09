package com.takaobrog.component.model

interface ConverterState {
    fun errorState(e: Throwable): ErrorState
}