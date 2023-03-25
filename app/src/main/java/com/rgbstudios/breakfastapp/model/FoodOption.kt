package com.rgbstudios.breakfastapp.model

data class FoodOption (
    val image: Int,
    val name: String,
    val price: Double,
    var quantity: Int = 0
)