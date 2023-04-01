package com.rgbstudios.breakfastapp.model

data class CartItem(
    val optionName: String,
    var optionQuantity: Int,
    var optionPrice: Double,
    var optionCost: Double = 0.0
)