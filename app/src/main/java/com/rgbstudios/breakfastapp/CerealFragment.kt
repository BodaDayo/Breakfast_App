package com.rgbstudios.breakfastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgbstudios.breakfastapp.adapter.CerealAdapter
import com.rgbstudios.breakfastapp.data.FoodStore
import com.rgbstudios.breakfastapp.data.FoodStore.cerealOptions
import com.rgbstudios.breakfastapp.databinding.FragmentCerealBinding
import com.rgbstudios.breakfastapp.model.CartItem
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [CerealFragment] allows a user to choose Cereals for the order.
 */
class CerealFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()
    private val datasource = cerealOptions

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentCerealBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCerealBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel
            val cerealOptions = datasource
            val adapter = CerealAdapter(cerealOptions, sharedViewModel)
            cerealRecyclerView.adapter = adapter
            cerealRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            nextButton.setOnClickListener { goToNextScreen(adapter) }
            cancelButton.setOnClickListener { cancelOrder() }
            buttonSpecial.setOnClickListener { checkOutSpecialOrder() }
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    private fun goToNextScreen(adapter: CerealAdapter) {

        updateCart(adapter)
        findNavController().navigate(R.id.action_cerealFragment_to_dairyFragment)
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
    private fun updateCart(adapter: CerealAdapter) {
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
        findNavController().navigate(R.id.action_cerealFragment_to_foodChoiceFragment)
    }
    private fun checkOutSpecialOrder(){
        val order = sharedViewModel.prepareSpecialOrder(FoodStore.specialFoodOption)
        sharedViewModel.setSpecialOrder(order)
        findNavController().navigate(R.id.action_cerealFragment_to_pickupFragment)
    }
}