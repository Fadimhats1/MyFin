package com.example.myfin.models.Transaction

import com.example.myfin.models.global.BaseRequest
import com.example.myfin.utils.enums.OrderBy
import com.example.myfin.utils.enums.OrderType

class RequestSort (val orderBy: OrderBy, val orderType: OrderType): BaseRequest()