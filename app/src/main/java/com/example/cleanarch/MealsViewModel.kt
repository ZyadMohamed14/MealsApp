package com.example.cleanarch

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.Constans
import com.example.data.remote.NetworkModule
import com.example.data.remote.RetrofitClient
import com.example.data.repo.MealsRepoImpl
import com.example.domain.entity.CategoryRespons
import com.example.domain.usecase.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel
@Inject constructor( @ApplicationContext private val context: Context,
                     private val getMealUseCase:GetMeals,
                     private val mealsRepoImpl: MealsRepoImpl):ViewModel(){


    private val _categeories:MutableStateFlow<CategoryRespons?> = MutableStateFlow(null)
    val categeories: StateFlow<CategoryRespons?> = _categeories
    fun getMealsFromRemote(){
        viewModelScope.launch {
            if(isWifiConnected(context)){

                // Fetch data from the remote API
                val categoryResponse = getMealUseCase.invoke()
                _categeories.value=categoryResponse
                // Insert the data into the Room database
                categoryResponse.categories.forEach {category ->
                    mealsRepoImpl.addMeal(category)
                }
            }
            else{
                getMealsFromLocal()
            }
        }
    }
    fun getMealsFromLocal(){
        viewModelScope.launch {
            val catgoryList=getMealUseCase.invokeLocal()
            _categeories.value?.categories=catgoryList
            Log.d("zyad", "meals: "+ catgoryList)
        }
    }

    fun isWifiConnected(context: Context): Boolean {
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
