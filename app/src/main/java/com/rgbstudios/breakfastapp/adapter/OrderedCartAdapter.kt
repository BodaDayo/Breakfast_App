package com.rgbstudios.breakfastapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgbstudios.breakfastapp.databinding.ListItemCartBinding
import com.rgbstudios.breakfastapp.model.OrderViewModel
import java.text.NumberFormat
import java.util.*

class OrderedCartAdapter(sharedViewModel: OrderViewModel) :
    RecyclerView.Adapter<OrderedCartAdapter.ViewHolder>() {

    private val cartItems = sharedViewModel.orderedCart.value ?: emptyList()

    class ViewHolder(val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.binding.apply {

            //set item quantity
            itemQuantity.text = cartItem.optionQuantity.toString()

            // set item name text
            itemName.text = cartItem.optionName

            // set item price
            val formattedPrice =
                NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(cartItem.optionPrice)
            itemPrice.text = formattedPrice

            // set item cost text
            val formattedCost =
                NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(cartItem.optionCost)
            itemCost.text = formattedCost
        }
    }

    override fun getItemCount() = cartItems.size

}

