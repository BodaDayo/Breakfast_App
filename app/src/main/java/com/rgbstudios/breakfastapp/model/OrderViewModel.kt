package com.rgbstudios.breakfastapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rgbstudios.breakfastapp.data.FoodStore
import java.text.NumberFormat
import java.util.*


class OrderViewModel : ViewModel() {

    private val _bakeryCart = MutableLiveData<List<FoodOption>>()

    private val _poultryCart = MutableLiveData<List<FoodOption>>()

    private val _breweryCart = MutableLiveData<List<FoodOption>>()

    private val _dessertsCart = MutableLiveData<List<FoodOption>>()

    private val _cerealCart = MutableLiveData<List<FoodOption>>()

    private val _dairyCart = MutableLiveData<List<FoodOption>>()

    private val _smoothieCart = MutableLiveData<List<FoodOption>>()

    private val _biscuitCart = MutableLiveData<List<FoodOption>>()

    private val _cart = MutableLiveData<List<CartItem>>()
    val cart: LiveData<List<CartItem>> = _cart

    private val _orderedCart = MutableLiveData<List<CartItem>>()
    val orderedCart: LiveData<List<CartItem>> = _orderedCart

    private val _localGA = MutableLiveData<String>()
    val localGA: LiveData<String> = _localGA

    private val _areaOption = MutableLiveData<String>()
    val areaOption: LiveData<String> = _areaOption

    private val _deliveryPrice = MutableLiveData<Double>()
    val deliveryPrice: LiveData<Double> = _deliveryPrice

    private val _fullAddress = MutableLiveData<String>()
    val fullAddress: LiveData<String> = _fullAddress

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber


    private var _bakeryCost = MutableLiveData<Double>()
    val bakeryCost: LiveData<String> = Transformations.map(_bakeryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private var _poultryCost = MutableLiveData<Double>()
    val poultryCost: LiveData<String> = Transformations.map(_poultryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private var _breweryCost = MutableLiveData<Double>()
    val breweryCost: LiveData<String> = Transformations.map(_breweryCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private var _dessertsCost = MutableLiveData<Double>()
    val dessertsCost: LiveData<String> = Transformations.map(_dessertsCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }

    private var _cerealCost = MutableLiveData<Double>()
    val cerealCost: LiveData<String> = Transformations.map(_cerealCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }
    private var _dairyCost = MutableLiveData<Double>()
    val dairyCost: LiveData<String> = Transformations.map(_dairyCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }
    private var _smoothieCost = MutableLiveData<Double>()
    val smoothieCost: LiveData<String> = Transformations.map(_smoothieCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }
    private var _biscuitCost = MutableLiveData<Double>()
    val biscuitCost: LiveData<String> = Transformations.map(_biscuitCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }


    private var _subTotalCost = MutableLiveData<Double>()
    val subTotalCost: LiveData<String> = Transformations.map(_subTotalCost) {
        NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(it)
    }
    private var _totalCost = MutableLiveData<Double>()
    val totalCost: LiveData<String> = Transformations.map(_totalCost) {
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

    fun addItemToCerealCart(item: FoodOption) {
        val cerealCurrentCart = _cerealCart.value ?: emptyList()
        val cartItemIndex = cerealCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _cerealCart.value = cerealCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = cerealCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _cerealCart.value = newCart
        }
        calculateCerealCost()
    }

    private fun calculateCerealCost() {
        val cerealCurrentCart = _cerealCart.value ?: emptyList()
        var cerealTotal = 0.0
        for (item in cerealCurrentCart) {
            cerealTotal += item.price * item.quantity
        }
        _cerealCost.value = cerealTotal
    }

    fun addItemToDairyCart(item: FoodOption) {
        val dairyCurrentCart = _dairyCart.value ?: emptyList()
        val cartItemIndex = dairyCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _dairyCart.value = dairyCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = dairyCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _dairyCart.value = newCart
        }
        calculateDairyCost()
    }

    private fun calculateDairyCost() {
        val dairyCurrentCart = _dairyCart.value ?: emptyList()
        var dairyTotal = 0.0
        for (item in dairyCurrentCart) {
            dairyTotal += item.price * item.quantity
        }
        _dairyCost.value = dairyTotal
    }

    fun addItemToSmoothieCart(item: FoodOption) {
        val smoothieCurrentCart = _smoothieCart.value ?: emptyList()
        val cartItemIndex = smoothieCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _smoothieCart.value = smoothieCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = smoothieCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _smoothieCart.value = newCart
        }
        calculateSmoothieCost()
    }

    private fun calculateSmoothieCost() {
        val smoothieCurrentCart = _smoothieCart.value ?: emptyList()
        var smoothieTotal = 0.0
        for (item in smoothieCurrentCart) {
            smoothieTotal += item.price * item.quantity
        }
        _smoothieCost.value = smoothieTotal
    }

    fun addItemToBiscuitCart(item: FoodOption) {
        val biscuitCurrentCart = _biscuitCart.value ?: emptyList()
        val cartItemIndex = biscuitCurrentCart.indexOfFirst { it.name == item.name }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _biscuitCart.value = biscuitCurrentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = biscuitCurrentCart.toMutableList().apply {
                set(cartItemIndex, item)
            }
            _biscuitCart.value = newCart
        }
        calculateBiscuitCost()
    }

    private fun calculateBiscuitCost() {
        val biscuitCurrentCart = _biscuitCart.value ?: emptyList()
        var biscuitTotal = 0.0
        for (item in biscuitCurrentCart) {
            biscuitTotal += item.price * item.quantity
        }
        _biscuitCost.value = biscuitTotal
    }


    fun addItemToCart(item: CartItem) {
        val currentCart = _cart.value ?: emptyList()
        val cartItemIndex = currentCart.indexOfFirst { it.optionName == item.optionName }

        if (cartItemIndex == -1) {
            // If the item is not already in the cart, add it
            _cart.value = currentCart.toMutableList().apply { add(item) }
        } else {
            // If the item is already in the cart, replace the existing item with the new item
            val newCart = currentCart.toMutableList().apply {
                set(cartItemIndex, item.copy(optionCost = item.optionQuantity * item.optionPrice))
            }
            _cart.value = newCart
        }
        calculateSubTotalCost()
        updateToOrderedCart()
    }

    private fun updateToOrderedCart() {
        val currentCart = cart.value ?: emptyList()
        val orderedItems = currentCart.filter { it.optionQuantity > 0 }
        _orderedCart.value = orderedItems
    }


    private fun calculateSubTotalCost() {
        val currentCart = _cart.value ?: emptyList()
        var total = 0.0
        for (item in currentCart) {
            item.optionCost = item.optionQuantity * item.optionPrice
            total += item.optionCost
        }
        _subTotalCost.value = total
        calculateTotalCost()
    }

    private fun calculateTotalCost() {
        val currentSubTotal = _subTotalCost.value ?: 0.0
        val currentDeliveryCost = _deliveryPrice.value ?: 0.0
        _totalCost.value = currentSubTotal + currentDeliveryCost
    }

    private fun clearCart() {
        _cart.value = emptyList()
        _subTotalCost.value = 0.0
    }


    fun setLocalGA(selectedLGA: String) {
        _localGA.value = selectedLGA
    }

    fun setAeaOption(selectedArea: String) {
        _areaOption.value = selectedArea
    }

    fun setDeliveryPrice(selectedPrice: Double) {
        _deliveryPrice.value = selectedPrice
        calculateTotalCost()
    }

    fun setFullAddress(inputtedText: String) {
        _fullAddress.value = inputtedText
    }

    fun setPhoneNumber(inputtedPhoneNumber: String) {
        _phoneNumber.value = inputtedPhoneNumber
    }


    fun resetOrder() {
        clearCart()
        resetInitialQuantities()

        val cartAndCosts = listOf(
            _bakeryCart to _bakeryCost,
            _poultryCart to _poultryCost,
            _breweryCart to _breweryCost,
            _dessertsCart to _dessertsCost,
            _cerealCart to _cerealCost,
            _dairyCart to _dairyCost,
            _smoothieCart to _smoothieCost,
            _biscuitCart to _biscuitCost
        )

        cartAndCosts.forEach { (cart, cost) ->
            cart.value = emptyList()
            cost.value = 0.0
        }

        _localGA.value = "Ife-Central"
        _areaOption.value = "OAU Campus"
        _fullAddress.value = ""
        _phoneNumber.value = ""
        _deliveryPrice.value = 0.99
        _orderedCart.value = emptyList()
        _totalCost.value = 0.0
    }


    private fun resetInitialQuantities() {
        val foodOptions = listOf(
            FoodStore.bakeryOptions,
            FoodStore.poultryOptions,
            FoodStore.breweryOptions,
            FoodStore.dessertsOptions,
            FoodStore.cerealOptions,
            FoodStore.dairyOptions,
            FoodStore.smoothieOptions,
            FoodStore.biscuitOptions
        )

        for (options in foodOptions) {
            for (foodOption in options) {
                foodOption.quantity = 0
            }
        }
    }

    fun setSpecialOrder(item: CartItem) {
        clearCart()
        addItemToCart(item)
    }

    fun prepareSpecialOrder(option: FoodOption): CartItem {
        return CartItem(
            optionName = option.name,
            optionQuantity = option.quantity,
            optionPrice = option.price,
            optionCost = option.quantity * option.price
        )
    }


}