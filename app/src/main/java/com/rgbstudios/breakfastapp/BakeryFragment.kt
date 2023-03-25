package com.rgbstudios.breakfastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgbstudios.breakfastapp.adapter.BakeryAdapter
import com.rgbstudios.breakfastapp.data.FoodStore.bakeryOptions
import com.rgbstudios.breakfastapp.databinding.FragmentBakeryBinding
import com.rgbstudios.breakfastapp.model.CartItem
import com.rgbstudios.breakfastapp.model.OrderViewModel


/**
 * [BakeryFragment] allows a user to choose a Pastry type for the order.
 */
class BakeryFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val datasource = bakeryOptions

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentBakeryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentBakeryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel
            val bakeryOptions = datasource
            val adapter = BakeryAdapter(bakeryOptions, sharedViewModel)
            bakeryRecyclerView.adapter = adapter
            bakeryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            bakeryFragment = this@BakeryFragment
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen(adapter: BakeryAdapter) {

        updateCart(adapter)
        findNavController().navigate(R.id.action_bakeryFragment_to_poultryFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /**
     * add the details of each option to the viewModel's cart
     */
    private fun updateCart(adapter: BakeryAdapter) {
        for (option in adapter.options) {
            val cartItem = CartItem(
                optionName = option.name,
                optionQuantity = option.quantity,
                optionPrice = option.price,
                optionCost = option.quantity * option.price
            )
            sharedViewModel.addItemToCart(cartItem)
        }
    }
}