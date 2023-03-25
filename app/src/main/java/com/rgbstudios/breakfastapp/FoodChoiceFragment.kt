package com.rgbstudios.breakfastapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rgbstudios.breakfastapp.databinding.FragmentFoodChoiceBinding
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [FoodChoiceFragment] allows a user to choose a Meal type for the order.
 */
class FoodChoiceFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_food_choice.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentFoodChoiceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFoodChoiceBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            menuOption1.setOnClickListener { goToNextScreen() }
            menuOption2.setOnClickListener { goToNextScreen() }
            menuOption3.setOnClickListener { goToNextScreen() }
            menuOption4.setOnClickListener { goToNextScreen() }
        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_foodChoiceFragment_to_bakeryFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
