package com.example.cleanarch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.CategoryEntity
import com.example.data.local.MealDao
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryRespons
import com.example.domain.usecase.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel
    @Inject constructor(private val getMealUseCase:GetMeals, private val categoryDao: MealDao) :ViewModel(){

    private val _categeories:MutableStateFlow<CategoryRespons?> = MutableStateFlow(null)
    val categeories: StateFlow<CategoryRespons?> = _categeories
    private val _localCategeories:MutableStateFlow<CategoryRespons?> = MutableStateFlow(null)
    val localcategeories: StateFlow<CategoryRespons?> = _categeories


        fun getMealsFromRemote(){
            viewModelScope.launch {
                try {
                   _categeories.value= getMealUseCase.invoke()
                }catch (e:Exception){
                    Log.d("benz", "getMeals: ${e.message}")
                }

            }
        }

     fun getMealsToLocal(){
         viewModelScope.launch {
             try {
                 // Fetch data from the remote API
                 val categoryResponse = getMealUseCase.invoke()
                 // Insert the data into the Room database
                 categoryResponse.categories.forEach {category ->
                     val categoryEntity = CategoryEntity(
                         idCategory = category.idCategory.toInt(),
                         strCategory= category.strCategory,
                         strCategoryDescription= category.strCategoryDescription,
                         strCategoryThumb= category.strCategoryThumb
                     )
                     categoryDao.insert(categoryEntity)
                     //categoryDao.insert(category as CategoryEntity)

                 }

             }catch (e:Exception){
                 Log.d("benz", "getMeals: ${e.message}")
             }

         }


    }
}