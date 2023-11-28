package com.example.cleanarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.data.remote.RetrofitClient
import com.example.data.repo.Constans
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MealActivity : AppCompatActivity() {
    @Inject
    lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        makeNetworkRequest()
    }
    private fun makeNetworkRequest() {
        val apiService = retrofitClient.getService(Constans.Base_Url)

        // Make the network request in a coroutine
        lifecycleScope.launch {
            try {
                val response = apiService.getMeals(Constans.Category_Base_Url)
                // Log the response
                Log.d("zyad", "Response: ${response.categories}")
            } catch (e: Exception) {
                // Handle errors
                Log.e("zyad", "Error: ${e.message}")
            }
        }
    }

}