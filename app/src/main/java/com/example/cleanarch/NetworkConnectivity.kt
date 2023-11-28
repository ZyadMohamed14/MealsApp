package com.example.cleanarch

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkConnectivity {
    private lateinit var context : Context

    fun initNetworkConnectivity(context: Context){
        this.context = context

    }

     fun isConnected() : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null) {
            // For Android 10 and above, use NetworkCapabilities to check for Wi-Fi
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                return networkCapabilities != null && networkCapabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI)
            } else {
                // For Android versions below 10, check the type of the network
                return networkInfo.type == ConnectivityManager.TYPE_WIFI
            }
        }
        return false
    }
}