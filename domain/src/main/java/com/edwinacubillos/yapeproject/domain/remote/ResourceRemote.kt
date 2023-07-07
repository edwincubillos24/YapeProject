package com.edwinacubillos.domain.remote

sealed class ResourceRemote<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Status? = null,
    val errorCode: Int? = null
) {
    class Success<T>(status: Status, data: T) : ResourceRemote<T>(status = status, data = data)
    class Error<T>(
        status: Status? = null,
        errorCode: Int? = null,
        message: String? = null,
        data: T? = null
    ) : ResourceRemote<T>(status = status, errorCode = errorCode, data = data, message = message)

}

enum class Status {
    Success {
        override fun toString(): String {
            return this.name
        }
    },
    Error {
        override fun toString(): String {
            return this.name
        }
    }
}
