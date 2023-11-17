package com.example.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cate_tabel")
data class CategoryEntity (@PrimaryKey(autoGenerate = true)val idCategory:Int,
val strCategory: String,
val strCategoryDescription: String,
val strCategoryThumb: String)
