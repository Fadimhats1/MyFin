package com.example.myfin.domains.global.model

import android.os.Parcelable

abstract class UIBaseData : Parcelable {
    abstract val isSuccess: Boolean
}