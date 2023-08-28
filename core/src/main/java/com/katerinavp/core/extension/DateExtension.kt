package com.katerinavp.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.convertDateToString(): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)

}