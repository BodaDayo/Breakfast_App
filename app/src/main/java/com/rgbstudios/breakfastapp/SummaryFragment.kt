package com.rgbstudios.breakfastapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgbstudios.breakfastapp.adapter.OrderedCartAdapter
import com.rgbstudios.breakfastapp.data.FoodVendors.listOfVendorEmails
import com.rgbstudios.breakfastapp.databinding.FragmentSummaryBinding
import com.rgbstudios.breakfastapp.model.OrderViewModel

/**
 * [SummaryFragment] contains a summary of the order details with a button to share the order
 * via another app.
 */
class SummaryFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentSummaryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel

            // Create the adapter with the list of ordered items
            val adapter = OrderedCartAdapter(sharedViewModel)

            summaryRecyclerView.adapter = adapter
            summaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            changeAddress.setOnClickListener { findNavController().navigate(R.id.action_summaryFragment_to_pickupFragment) }
            placeOrderButton.setOnClickListener { sendOrder() }
            cancelOrderText.setOnClickListener { cancelOrder() }
        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    private fun sendOrder() {
        val viewModel = sharedViewModel
        val orderedCart = viewModel.orderedCart.value ?: emptyList()
        val orderSummary = StringBuilder()
        orderSummary.append(getString(R.string.order_summary_request)).append("\n\n")
        val orderItems = orderedCart.joinToString("\n") { "${it.optionName}: ${it.optionQuantity}" }
        orderSummary.append("$orderItems\n\n")

        orderSummary.append(getString(R.string.order_summary_address_text)).append("\n\n")
        orderSummary.append(
            "%s, %s, %s.\n\n".format(
                viewModel.fullAddress.value,
                viewModel.areaOption.value,
                viewModel.localGA.value
            )
        )
        orderSummary.append(getString(R.string.order_summary_phone_number)).append(" ${viewModel.phoneNumber.value}\n\n")
        orderSummary.append(getString(R.string.order_summary_thank_you))

        val intent = Intent(Intent.ACTION_SEND).setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_breakfast_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary.toString())
            .putExtra(Intent.EXTRA_EMAIL, listOfVendorEmails)
        startActivity(Intent.createChooser(intent, getString(R.string.send_email)))
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
        findNavController().navigate(R.id.action_summaryFragment_to_foodChoiceFragment)
    }
}