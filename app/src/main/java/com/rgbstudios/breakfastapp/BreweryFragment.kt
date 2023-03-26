package com.rgbstudios.breakfastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgbstudios.breakfastapp.adapter.BreweryAdapter
import com.rgbstudios.breakfastapp.data.FoodStore
import com.rgbstudios.breakfastapp.databinding.FragmentBreweryBinding
import com.rgbstudios.breakfastapp.model.CartItem
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [BreweryFragment] allows a user to choose a Coffee type for the order.
 */
class BreweryFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val datasource = FoodStore.breweryOptions

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentBreweryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBreweryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel
            val breweryOptions = datasource
            val adapter = BreweryAdapter(breweryOptions, sharedViewModel)
            breweryRecyclerView.adapter = adapter
            breweryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            nextButton.setOnClickListener { goToNextScreen(adapter) }
            cancelButton.setOnClickListener { cancelOrder() }
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    private fun goToNextScreen(adapter: BreweryAdapter) {

        updateCart(adapter)
        findNavController().navigate(R.id.action_breweryFragment_to_dessertsFragment)
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
    private fun updateCart(adapter: BreweryAdapter) {
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

    private fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_breweryFragment_to_foodChoiceFragment)
    }
}