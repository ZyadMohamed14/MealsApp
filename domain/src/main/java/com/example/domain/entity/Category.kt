package com.example.domain.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cate_tabel")
data class Category(@PrimaryKey(autoGenerate = true)val id:Int=0,
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)