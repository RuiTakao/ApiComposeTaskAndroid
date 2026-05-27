package com.takaobrog.core.util

import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeProviderImpl @Inject constructor(): TimeProvider {
    override fun now(): String = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
}