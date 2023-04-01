package com.rgbstudios.breakfastapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rgbstudios.breakfastapp.adapter.LocationDropdownAdapter
import com.rgbstudios.breakfastapp.data.LocationData.ifeCentralAreas
import com.rgbstudios.breakfastapp.data.LocationData.ifeEastAreas
import com.rgbstudios.breakfastapp.data.LocationData.ifeNorthAreas
import com.rgbstudios.breakfastapp.data.LocationData.lga
import com.rgbstudios.breakfastapp.databinding.FragmentPickupBinding
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [PickupFragment] allows the user to enter delivery details for the breakfast order.
 */
class PickupFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_pickup.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentPickupBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel


            // Set up location dropdown options
            val locationOptions = lga
            val locationAdapter =
                LocationDropdownAdapter(requireContext(), locationOptions)
            locationOptionsMenu.setAdapter(locationAdapter)

            // Set up OnItemClickListener for locationOptionsMenu
            locationOptionsMenu.setOnItemClickListener { parent, _, position, _ ->
                val selectedLGA = parent.getItemAtPosition(position).toString()
                sharedViewModel.setLocalGA(selectedLGA)
            }


            // Set up area dropdown options
            sharedViewModel.localGA.observe(viewLifecycleOwner) { localGAValue ->
                val areaOptions = when (localGAValue) {
                    "Ife-North" -> ifeNorthAreas
                    "Ife-Central" -> ifeCentralAreas
                    else -> ifeEastAreas
                }
                val areaAdapter =
                    LocationDropdownAdapter(requireContext(), areaOptions)
                areaOptionsMenu.setAdapter(areaAdapter)
            }

            // Set up OnItemClickListener for areaOptionsMenu
            areaOptionsMenu.setOnItemClickListener { parent, _, position, _ ->
                val selectedArea = parent.getItemAtPosition(position).toString()
                sharedViewModel.setAeaOption(selectedArea)
                if (selectedArea == "OAU Campus") {
                    sharedViewModel.setDeliveryPrice(0.99)
                } else sharedViewModel.setDeliveryPrice(
                    499.99
                )
            }

            // Set the default selected location option to "Ife-Central"
            locationOptionsMenu.setText(lga[1], false)

            // Set the default selected area option to "OAU Campus"
            areaOptionsMenu.setText(ifeCentralAreas[0], false)

            nextButton.setOnClickListener {
                val phoneNumberInput = phoneInputLayout.editText?.text.toString().trim()
                val addressInput = addressInputLayout.editText?.text.toString().trim()
                val currentCart = sharedViewModel.orderedCart.value ?: emptyList()

                if (currentCart.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "No item in cart",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (addressInput.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Please type in your address",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (phoneNumberInput.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Please input your Phone number",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                // Check if the phone number input matches the regex pattern
                val regexPattern = Regex("^\\d{11}\$")
                if (!regexPattern.matches(phoneNumberInput)) {
                    Toast.makeText(
                        requireContext(),
                        "Please type in the correct number format (e.g. 08012345678)",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                sharedViewModel.setPhoneNumber(phoneNumberInput)
                sharedViewModel.setFullAddress(addressInput)

                goToNextScreen()
            }
            cancelButton.setOnClickListener { cancelOrder() }
        }
    }

    /**
     * Navigate to the next screen to see the order summary.
     */
    private fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_foodChoiceFragment)
    }
}