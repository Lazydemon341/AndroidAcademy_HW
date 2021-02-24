package com.avvlas.androidacademyhomework.ui.viewstate

sealed class ViewState<out ResultType>{

    /**
     *Describes success state of the ui with
     * [data] shown
     */
    data class Success<out ResultType>(
        val data : ResultType
    ) : ViewState<ResultType>()

    /**
     * Describes loading state of the ui
     */
    object Loading : ViewState<Nothing>()

    /**
     * Describes error state of the ui
     */
    object Error : ViewState<Nothing>()
}