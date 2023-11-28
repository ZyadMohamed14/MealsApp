package com.example.data.local
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


data class CategoryEntity (
val strCategory: String,
val strCategoryDescription: String,
val strCategoryThumb: String)
