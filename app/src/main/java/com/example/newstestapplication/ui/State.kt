package com.example.newstestapplication.ui

sealed class State<ResultType> {

    data class Success<ResultType>(val data: ResultType) : State<ResultType>()

    data class Error<ResultType>(val message: String = "") : State<ResultType>()

    companion object {
        fun <ResultType> success(data: ResultType): State<ResultType> = Success(data)

        fun <ResultType> error(message: String): State<ResultType> = Error(message)
    }
}