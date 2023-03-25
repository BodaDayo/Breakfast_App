package com.rgbstudios.breakfastapp.data

import com.rgbstudios.breakfastapp.R
import com.rgbstudios.breakfastapp.model.FoodOption

object FoodStore {

    val bakeryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.bakery_items, "White Bread", 200.0),
        FoodOption(R.drawable.bakery_items, "Wheat Bread", 250.0),
        FoodOption(R.drawable.bakery_items, "Sourdough Bread", 300.0),
        FoodOption(R.drawable.bakery_items, "Rye Bread", 350.0),
        // FoodOption(R.drawable.croissant, "Croissant", 2.99),
        // FoodOption(R.drawable.muffin, "Muffin", 1.99),
        // FoodOption(R.drawable.scone, "Scone", 2.49),
        // FoodOption(R.drawable.bagel, "Bagel", 1.49)
    )

    val poultryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.eggs, "Scrambled Eggs", 40.0),
        FoodOption(R.drawable.eggs, "Fried Eggs", 60.0),
        FoodOption(R.drawable.eggs, "Boiled Eggs", 30.0),
        FoodOption(R.drawable.eggs, "Roasted Eggs", 40.0),
        FoodOption(R.drawable.eggs, "Raw Eggs", 20.0),
        FoodOption(R.drawable.eggs, "Eggs Pudding", 40.0),

    )

}