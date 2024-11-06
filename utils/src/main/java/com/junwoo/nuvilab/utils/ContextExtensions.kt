package com.junwoo.nuvilab.utils

import android.content.Context
import android.net.ConnectivityManager

val Context.connectivityManger
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager