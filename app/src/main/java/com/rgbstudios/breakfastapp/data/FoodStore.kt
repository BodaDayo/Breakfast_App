package com.rgbstudios.breakfastapp.data

import com.rgbstudios.breakfastapp.R
import com.rgbstudios.breakfastapp.model.FoodOption

object FoodStore {

    val specialFoodOption = FoodOption(R.drawable.bakery, "Chef\'s Special Sunrise Surprise", 3000.0, 1)

    val bakeryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.white, "White Bread", 600.0),
        FoodOption(R.drawable.wheat, "Wheat Bread", 850.0),
        FoodOption(R.drawable.sourdough, "Sourdough Bread", 900.0),
        FoodOption(R.drawable.rye, "Rye Bread", 650.0),
        FoodOption(R.drawable.pancake, "Pancake", 500.0),
        FoodOption(R.drawable.croissant, "Croissant", 280.0),
        FoodOption(R.drawable.muffin, "Muffin", 200.0),
        FoodOption(R.drawable.scone, "Scone", 100.0),
        FoodOption(R.drawable.bagel, "Bagel", 200.0)
    )

    val poultryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.scrambled, "Scrambled Eggs", 150.0),
        FoodOption(R.drawable.fried, "Fried Eggs", 120.0),
        FoodOption(R.drawable.boiled, "Boiled Eggs", 100.0),
        FoodOption(R.drawable.roasted, "Roasted Eggs", 120.0),
        FoodOption(R.drawable.raw, "Raw Eggs", 80.0),
        FoodOption(R.drawable.pudding, "Eggs Pudding", 250.0),

        )

    val breweryOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.coffee2, "Espresso", 500.0),
        FoodOption(R.drawable.hot_chocolate, "Hot chocolate", 450.0),
        FoodOption(R.drawable.tea, "Tea", 400.0),
        FoodOption(R.drawable.coffee3, "Cappuccino", 600.0),
        FoodOption(R.drawable.coffee4, "Americano", 500.0),
        FoodOption(R.drawable.coffeelatte, "Latte", 450.0),
        FoodOption(R.drawable.tea2, "Green Tea", 450.0),
        FoodOption(R.drawable.coffeelatte2, "Mocha", 450.0),
        FoodOption(R.drawable.orange_juice, "Orange juice", 350.0),
        FoodOption(R.drawable.water, "Water", 200.0),

        )

    val dessertsOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.yogurt, "Yogurt", 400.0),
        FoodOption(R.drawable.greek_yogurt, "Greek yogurt", 4500.0),
        FoodOption(R.drawable.tiramisu, "Tiramisu", 400.0),
        FoodOption(R.drawable.ice_cream, "Crème Brûlée", 600.0),
        FoodOption(R.drawable.fruit_tart, "Fruit Tart", 550.0),

        )

    val cerealOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.golden_morn, "Nestlé Golden Morn", 350.0),
        FoodOption(R.drawable.oatmeal_cereal, "Quaker Oats", 400.0),
        FoodOption(R.drawable.corn_flakes, "Kellogg’s Corn Flakes", 450.0),
        FoodOption(R.drawable.milo_coco_balls, "Nestlé Milo Cereal", 400.0),
        FoodOption(R.drawable.crunchy_cereal, "Nasco Corn Flakes", 500.0),
        FoodOption(R.drawable.coco_pops, "Kellogg’s Coco Pops", 400.0),
        FoodOption(R.drawable.weetabix, "Weetabix", 500.0),
        FoodOption(R.drawable.rice_krisp, "Kellogg’s Rice Krispies", 350.0),
        )

    val dairyOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.whole_milk, "Whole Milk", 400.0),
        FoodOption(R.drawable.soy_milk, "Soy Milk", 600.0),
        FoodOption(R.drawable.almond_milk, "Almond Milk", 300.0),
        FoodOption(R.drawable.coconut_milk, "Coconut Milk", 400.0),
        FoodOption(R.drawable.almond_milk, "Hazelnut milk", 300.0),
        FoodOption(R.drawable.soy_milk, "Oat milk", 400.0),
    )

    val smoothieOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.protein_shakes, "Strawberry Banana Smoothie", 400.0),
        FoodOption(R.drawable.tropical_smooth, "Tropical Smoothie", 300.0),
        FoodOption(R.drawable.blueberry_smooth, "Blueberry Smoothie", 200.0),
        FoodOption(R.drawable.choco_shake, "Chocolate Shake", 400.0),
        FoodOption(R.drawable.protein_shakes, "Strawberry Shake", 400.0),
        FoodOption(R.drawable.oreoshake, "Oreo Shake", 600.0),
    )
    val biscuitOptions: List<FoodOption> = listOf(
        FoodOption(R.drawable.buttermilk_bis, "Buttermilk Biscuits", 200.0),
        FoodOption(R.drawable.oatmeal_bis, "Oatmeal Cookies", 300.0),
        FoodOption(R.drawable.blueberry_bis, "Blueberry Biscuits", 300.0),
        FoodOption(R.drawable.cinnamon_raison_bis, "Cinnamon Raisin Biscuits", 200.0),
        FoodOption(R.drawable.chocolate_chip_bis, "Chocolate Chip Cookies", 400.0),
        FoodOption(R.drawable.oreos_bis, "Oreos", 500.0),
    )


}