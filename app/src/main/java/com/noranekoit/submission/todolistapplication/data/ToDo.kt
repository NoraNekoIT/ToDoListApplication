package com.noranekoit.submission.todolistapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDo(
    var note: String
) : Parcelable