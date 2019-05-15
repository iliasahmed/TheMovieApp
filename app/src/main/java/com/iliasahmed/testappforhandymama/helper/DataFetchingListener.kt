package com.iliasahmed.testappforhandymama.helper

interface DataFetchingListener<T> {
    fun onDataFetched(response: T)
    fun onFailed(status: Int)
}