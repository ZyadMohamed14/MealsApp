package com.example.cleanarch


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import androidx.lifecycle.lifecycleScope
import com.example.cleanarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:MealsViewModel by viewModels<MealsViewModel>()
    @Inject
    lateinit var mealsAdapter: MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isWifiConnected(applicationContext)) {
            // If connected to Wi-Fi, fetch and display remote data
            viewModel.getMealsFromRemote()
        } else {
            // If not connected to Wi-Fi, fetch and display local data
            viewModel.getMealsToLocal()
        }

    lifecycleScope.launch {
        viewModel.categeories.collect{
            mealsAdapter.submitList(it?.categories)
            binding.categoryRv.adapter=mealsAdapter
        }
    }
    }

}

fun isWifiConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo

    if (networkInfo != null) {
        // For Android 10 and above, use NetworkCapabilities to check for Wi-Fi
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            // For Android versions below 10, check the type of the network
            return networkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }
    return false
}
