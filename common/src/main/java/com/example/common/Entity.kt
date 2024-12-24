package com.example.common

sealed class Entity<out T : Any>{
    data class Success<out T : Any>(val data: T?, val code: Int? = null) : Entity<T>() {
        override fun <K : Any> map(mapper: (oldValue: T?) -> K?): Success<K> {
            return Success(mapper.invoke(data), code)
        }
    }
    data class Error<out T : Any>(val exceptionMessage: String, val localData: T? = null) :
        Entity<T>() {
        override fun <K : Any> map(mapper: (oldValue: T?) -> K?): Error<K> {
            return Error(exceptionMessage, mapper.invoke(localData))
        }
    }
    abstract fun <K : Any> map(mapper: (oldValue: T?) -> K?): Entity<K>
}
