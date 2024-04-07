package com.example.myfin.models

import com.example.myfin.utils.enum.OrderBy
import com.example.myfin.utils.enum.OrderType

data class Sort (val orderBy: OrderBy, val orderType: OrderType): BaseRequest()