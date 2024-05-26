package com.example.myfin.models.Transaction

import com.example.myfin.models.global.BaseResponse

data class ResponseDeleteTransaction(
    override val isSuccess: Boolean,
    override val message: String?
) : BaseResponse<ResponseTransactionModel?>(isSuccess, message = message)