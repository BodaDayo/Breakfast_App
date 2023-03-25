package com.rgbstudios.breakfastapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rgbstudios.breakfastapp.databinding.FragmentStartBinding
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * This is the first screen of the Breakfast Order app.
 */
class StartFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        // Hide the action bar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Set up the button click listeners
            getAMeal.setOnClickListener { startOrder() }
        }
    }

    /**
     * Start an order and navigate to the next screen.
     */
    fun startOrder() {
        findNavController().navigate(R.id.action_startFragment_to_foodChoiceFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        // Show the action bar
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

}