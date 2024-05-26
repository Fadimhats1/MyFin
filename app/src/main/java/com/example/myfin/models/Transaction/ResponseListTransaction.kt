package com.example.myfin.models.Transaction

import com.example.myfin.models.global.BaseResponse

data class ResponseListTransaction(
    override val isSuccess: Boolean,
    override val data: List<ResponseTransactionModel>?,
) : BaseResponse<List<ResponseTransactionModel>?>(isSuccess, data)