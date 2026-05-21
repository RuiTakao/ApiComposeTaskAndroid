package com.takaobrog.apicomposetask.screen.task_edit.model

import com.takaobrog.component.model.ErrorState

sealed class FrontLayerState {
    object Idle : FrontLayerState()
    object Loading : FrontLayerState()
    data class Error(val error: ErrorState) : FrontLayerState()
}