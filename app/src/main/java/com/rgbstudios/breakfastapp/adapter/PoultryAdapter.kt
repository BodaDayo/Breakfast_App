package com.rgbstudios.breakfastapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgbstudios.breakfastapp.databinding.FoodOptionBinding
import com.rgbstudios.breakfastapp.model.CartItem
import com.rgbstudios.breakfastapp.model.FoodOption
import com.rgbstudios.breakfastapp.model.OrderViewModel
import java.text.NumberFormat
import java.util.*


class PoultryAdapter(val options: List<FoodOption>, sharedViewModel: OrderViewModel) :
    RecyclerView.Adapter<PoultryAdapter.ViewHolder>() {
    private val viewModel = sharedViewModel

    class ViewHolder(val binding: FoodOptionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoultryAdapter.ViewHolder {
        val binding =
            FoodOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoultryAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoultryAdapter.ViewHolder, position: Int) {
        val option = options[position]
        holder.binding.apply {
            optionImage.setImageResource(option.image)
            optionName.text = option.name

            val formattedPrice =
                NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(option.price)
            optionPrice.text = formattedPrice

            optionMinusButton.setOnClickListener {
                if (option.quantity > 0) {
                    option.quantity--
                    optionQuantity.text = option.quantity.toString()
                    updateCart(option)
                }
            }
            optionPlusButton.setOnClickListener {
                option.quantity++
                optionQuantity.text = option.quantity.toString()
                updateCart(option)
            }
            val currentQuantity = option.quantity.toString()
            optionQuantity.text = currentQuantity
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    /**
     * adds the option to the cart
     */

    private fun updateCart(option: FoodOption) {
        val cartItem = CartItem(
            optionName = option.name,
            optionQuantity = option.quantity,
            optionPrice = option.price,
            optionCost = option.quantity * option.price
        )
        viewModel.addItemToCart(cartItem)
        viewModel.addItemToPoultryCart(option)
    }

}