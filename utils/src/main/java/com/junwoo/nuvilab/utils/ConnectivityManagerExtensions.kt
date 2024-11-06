package com.junwoo.nuvilab.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun ConnectivityManager.isNetworkAvailable(): Boolean {
    val network = this.activeNetwork
    val capabilities = this.getNetworkCapabilities(network)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}