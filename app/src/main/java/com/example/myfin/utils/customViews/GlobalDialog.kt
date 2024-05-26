package com.example.myfin.utils.customViews

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import java.lang.IllegalStateException

class GlobalDialog(private val onCreateDialog: (FragmentActivity) -> Dialog) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            onCreateDialog(it)
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}