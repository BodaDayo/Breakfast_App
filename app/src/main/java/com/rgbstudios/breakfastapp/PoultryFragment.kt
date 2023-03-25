package com.rgbstudios.breakfastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgbstudios.breakfastapp.adapter.PoultryAdapter
import com.rgbstudios.breakfastapp.data.FoodStore.poultryOptions
import com.rgbstudios.breakfastapp.databinding.FragmentPoultryBinding
import com.rgbstudios.breakfastapp.model.CartItem
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [PoultryFragment] allows a user to choose Eggs for the order.
 */
class PoultryFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val datasource = poultryOptions

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentPoultryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPoultryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel
            val poultryOptions = datasource
            val adapter = PoultryAdapter(poultryOptions, sharedViewModel)
            poultryRecyclerView.adapter = adapter
            poultryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            nextButton.setOnClickListener { goToNextScreen(adapter) }
            cancelButton.setOnClickListener { cancelOrder() }
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    private fun goToNextScreen(adapter: PoultryAdapter) {

        updateCart(adapter)
        findNavController().navigate(R.id.action_poultryFragment_to_breweryFragment)
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
    private fun updateCart(adapter: PoultryAdapter) {
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
        findNavController().navigate(R.id.action_poultryFragment_to_foodChoiceFragment)
    }
}