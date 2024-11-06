package com.junwoo.nuvilab

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.junwoo.nuvilab.utils.connectivityManger

class NetworkMonitor(private val context: Context, private val onNetworkAvailable: () -> Unit) {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onNetworkAvailable()
        }
    }

    fun startMonitoring() {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        context.connectivityManger.registerNetworkCallback(request, networkCallback)
    }

    fun stopMonitoring() {
        context.connectivityManger.unregisterNetworkCallback(networkCallback)
    }
}