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

    private var _subTotalCost = MutableLiveData<Double>()
    val subTotalCost: LiveData<String> = Transformations.map(_subTotalCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }


    private val _bakeryCart = MutableLiveData<List<FoodOption>>()

    private var _bakeryCost = MutableLiveData<Double>()
    val bakeryCost: LiveData<String> = Transformations.map(_bakeryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private val _poultryCart = MutableLiveData<List<FoodOption>>()

    private var _poultryCost = MutableLiveData<Double>()
    val poultryCost: LiveData<String> = Transformations.map(_poultryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private val _breweryCart = MutableLiveData<List<FoodOption>>()

    private var _breweryCost = MutableLiveData<Double>()
    val breweryCost: LiveData<String> = Transformations.map(_breweryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private val _dessertsCart = MutableLiveData<List<FoodOption>>()

    private var _dessertsCost = MutableLiveData<Double>()
    val dessertsCost: LiveData<String> = Transformations.map(_dessertsCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private val _localGA = MutableLiveData<String>()
    val localGA: LiveData<String> = _localGA

    private val _deliveryPrice = MutableLiveData<Double>()
    val deliveryPrice: LiveData<Double> = _deliveryPrice

    private val _fullAddress = MutableLiveData<String>()
    val fullAddress: LiveData<String> = _fullAddress

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

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

    fun addItemToBreweryCart(item: FoodOption) {
        val breweryCurrentCart = _breweryCart.value ?: emptyList()
        val cartItemIndex = breweryCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _breweryCart.value = breweryCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = breweryCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _breweryCart.value = newCart
        }
        calculateBreweryCost()
    }

    private fun calculateBreweryCost() {
        val breweryCurrentCart = _breweryCart.value ?: emptyList()
        var breweryTotal = 0.0
        for (item in breweryCurrentCart) {
            breweryTotal += item.price * item.quantity
        }
        _breweryCost.value = breweryTotal
    }

    fun addItemToDessertsCart(item: FoodOption) {
        val dessertsCurrentCart = _dessertsCart.value ?: emptyList()
        val cartItemIndex = dessertsCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _dessertsCart.value = dessertsCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = dessertsCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _dessertsCart.value = newCart
        }
        calculateDessertsCost()
    }

    private fun calculateDessertsCost() {
        val dessertsCurrentCart = _dessertsCart.value ?: emptyList()
        var dessertsTotal = 0.0
        for (item in dessertsCurrentCart) {
            dessertsTotal += item.price * item.quantity
        }
        _dessertsCost.value = dessertsTotal
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

    fun setLocalGA(selectedLGA: String) {
        _localGA.value = selectedLGA
    }
    fun setDeliveryPrice(selectedPrice: Double) {
        _deliveryPrice.value = selectedPrice
    }
    fun setFullAddress(inputtedText: String) {
        _fullAddress.value = inputtedText
    }
    fun setPhoneNumber(inputtedPhoneNumber: String) {
        _phoneNumber.value = inputtedPhoneNumber
    }

    fun resetOrder() {
        _cart.value = emptyList()
        _subTotalCost.value = 0.0

        _bakeryCart.value = emptyList()
        _bakeryCost.value = 0.0

        _poultryCart.value = emptyList()
        _poultryCost.value = 0.0

        _breweryCart.value = emptyList()
        _breweryCost.value = 0.0

        _dessertsCart.value = emptyList()
        _dessertsCost.value = 0.0

        _localGA.value = "Ife-Central"

        _fullAddress.value = ""

        _deliveryPrice.value = 500.0
    }

}