package com.edwinacubillos.yapeproject.data.server

import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.yapeproject.domain.remote.WrappedResponse

open class WrappedResponseHandler {
    fun <T : Any?> handleSuccess(data: WrappedResponse<T>): ResourceRemote<WrappedResponse<T>>{
        return ResourceRemote.Success(data.status, data)
    }
}
