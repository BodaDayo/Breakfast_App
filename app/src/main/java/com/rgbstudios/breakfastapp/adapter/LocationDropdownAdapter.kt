package com.rgbstudios.breakfastapp.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.rgbstudios.breakfastapp.model.OrderViewModel

class LocationDropdownAdapter(context: Context, private val items: List<String>, sharedViewModel: OrderViewModel) :
    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items) {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): String? {
        return items[position]
    }
}