package com.example.myfin.models.global

open class BaseResponse <T>(open val isSuccess: Boolean, open val data: T? = null, open val message: String? = null)