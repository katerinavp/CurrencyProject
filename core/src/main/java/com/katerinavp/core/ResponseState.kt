package com.katerinavp.core


sealed class ResponseState<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResponseState<T>()
    data class Error(val error: Throwable) : ResponseState<Nothing>()
    object Empty : ResponseState<Nothing>()

}
