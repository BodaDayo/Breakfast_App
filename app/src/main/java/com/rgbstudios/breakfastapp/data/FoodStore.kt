package com.rgbstudios.breakfastapp.data

import com.rgbstudios.breakfastapp.R
import com.rgbstudios.breakfastapp.model.FoodOption

object FoodStore {

    val bakeryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.bakery_items, "White Bread", 200.0),
        FoodOption(R.drawable.bakery_items, "Wheat Bread", 250.0),
        FoodOption(R.drawable.bakery_items, "Sourdough Bread", 300.0),
        FoodOption(R.drawable.bakery_items, "Rye Bread", 350.0),
        FoodOption(R.drawable.pastries, "Croissant", 280.99),
        FoodOption(R.drawable.sandwiches, "Muffin", 100.99),
        FoodOption(R.drawable.bakery_items, "Scone", 2.49),
        FoodOption(R.drawable.pastries, "Bagel", 1.49)
    )

    val poultryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.eggs, "Scrambled Eggs", 40.0),
        FoodOption(R.drawable.eggs, "Fried Eggs", 60.0),
        FoodOption(R.drawable.eggs, "Boiled Eggs", 30.0),
        FoodOption(R.drawable.eggs, "Roasted Eggs", 40.0),
        FoodOption(R.drawable.eggs, "Raw Eggs", 20.0),
        FoodOption(R.drawable.eggs, "Eggs Pudding", 40.0),

    )

    val breweryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.coffee, "Espresso", 40.0),
        FoodOption(R.drawable.coffee, "Cappuccino", 60.0),
        FoodOption(R.drawable.coffee, "Americano", 30.0),
        FoodOption(R.drawable.coffee, "Latte", 40.0),
        FoodOption(R.drawable.coffee, "Mocha", 20.0),
        FoodOption(R.drawable.coffee, "Protein shakes", 40.0),
        FoodOption(R.drawable.coffee, "Tea", 40.0),
        FoodOption(R.drawable.coffee, "Orange juice", 20.0),
        FoodOption(R.drawable.coffee, "Hot chocolate", 40.0),
        FoodOption(R.drawable.coffee, "Water", 50.0),

        )

    val dessertsOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.tea, "Yogurt", 40.0),
        FoodOption(R.drawable.coffee, "Whipped cream", 60.0),
        FoodOption(R.drawable.eggs, "Greek yogurt", 30.0),
        FoodOption(R.drawable.sandwiches, "Roasted Eggs", 40.0),
        FoodOption(R.drawable.pastries, "Fruit salad", 20.0),
        FoodOption(R.drawable.bakery_items, "Eggs Pudding", 40.0),

        )

}