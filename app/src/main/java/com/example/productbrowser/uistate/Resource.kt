package com.example.productbrowser.uistate

sealed class Resource<out T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: String): Resource<T>()
}