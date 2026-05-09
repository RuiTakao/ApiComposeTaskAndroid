package com.takaobrog.component.model

import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ConverterStateImpl @Inject constructor() : ConverterState {
    override fun errorState(e: Throwable): ErrorState {
        return when (e) {
            is ConnectException,
            is UnknownHostException,
                -> ErrorState.NetworkError

            else -> ErrorState.SystemError(message = e.message)
        }
    }
}