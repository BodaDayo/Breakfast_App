package com.rgbstudios.breakfastapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.util.*

data class CartItem(
    val optionName: String,
    var optionQuantity: Int,
    var optionPrice: Double,
    var optionCost: Double = 0.0
)

class OrderViewModel : ViewModel() {

    private val _cart = MutableLiveData<List<CartItem>>()
    val cart: LiveData<List<CartItem>> = _cart

    private var _subTotalCost = MutableLiveData<Double>(0.0)
    val subTotalCost: LiveData<String> = Transformations.map(_subTotalCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }


    private val _bakeryCart = MutableLiveData<List<FoodOption>>()

    private var _bakeryCost = MutableLiveData(0.0)
    val bakeryCost: LiveData<String> = Transformations.map(_bakeryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private val _poultryCart = MutableLiveData<List<FoodOption>>()

    private var _poultryCost = MutableLiveData(0.0)
    val poultryCost: LiveData<String> = Transformations.map(_poultryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    init {
        resetOrder()
    }



    fun addItemToBakeryCart(item: FoodOption) {
        val bakeryCurrentCart = _bakeryCart.value ?: emptyList()
        val cartItemIndex = bakeryCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _bakeryCart.value = bakeryCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = bakeryCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _bakeryCart.value = newCart
        }
        calculateBakeryCost()
    }

    private fun calculateBakeryCost() {
        val bakeryCurrentCart = _bakeryCart.value ?: emptyList()
        var bakeryTotal = 0.0
        for (item in bakeryCurrentCart) {
            bakeryTotal += item.price * item.quantity
        }
        _bakeryCost.value = bakeryTotal
    }

    fun addItemToPoultryCart(item: FoodOption) {
        val poultryCurrentCart = _poultryCart.value ?: emptyList()
        val cartItemIndex = poultryCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _poultryCart.value = poultryCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = poultryCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _poultryCart.value = newCart
        }
        calculatePoultryCost()
    }

    private fun calculatePoultryCost() {
        val poultryCurrentCart = _poultryCart.value ?: emptyList()
        var poultryTotal = 0.0
        for (item in poultryCurrentCart) {
            poultryTotal += item.price * item.quantity
        }
        _poultryCost.value = poultryTotal
    }





    fun setCartItems(cartItems: List<CartItem>) {
        _cart.value = cartItems
        calculateSubTotalCost()
    }

    fun addItemToCart(item: CartItem) {
        val currentCart = _cart.value ?: emptyList()
        val cartItemIndex = currentCart.indexOfFirst { it.optionName == item.optionName }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _cart.value = currentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val cartItem = currentCart[cartItemIndex]
            val newCart = currentCart.toMutableList().apply {
                set(cartItemIndex, item.copy(optionCost = item.optionQuantity * item.optionPrice))
            }
            _cart.value = newCart
        }
        calculateSubTotalCost()
    }

    fun removeItemFromCart(index: Int) {
        val currentCart = _cart.value ?: emptyList()
        if (index in currentCart.indices) {
            val updatedCart = currentCart.toMutableList().apply {
                removeAt(index)
            }
            _cart.value = updatedCart
            calculateSubTotalCost()
        }
    }

    fun clearCart() {
        _cart.value = emptyList()
        _subTotalCost.value = 0.0
    }

    private fun calculateSubTotalCost() {
        val currentCart = _cart.value ?: emptyList()
        var total = 0.0
        for (item in currentCart) {
            item.optionCost = item.optionQuantity * item.optionPrice
            total += item.optionCost
        }
        _subTotalCost.value = total
    }

    fun resetOrder() {
        _cart.value = emptyList()
        _subTotalCost.value = 0.0

        _bakeryCart.value = emptyList()
        _bakeryCost.value = 0.0
        _poultryCart.value = emptyList()
        _poultryCost.value = 0.0
    }


}