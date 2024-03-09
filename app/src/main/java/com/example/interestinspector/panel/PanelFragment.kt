package com.example.interestinspector.panel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.interestinspector.R
import com.example.interestinspector.common.observeNonNull
import com.example.interestinspector.databinding.FragmentPanelBinding

class PanelFragment : Fragment() {

    private lateinit var binding: FragmentPanelBinding
    private val panelViewModel: PanelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPanelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        var days: MutableList<String> = mutableListOf()
        for (i in 1..32) {
            days.add(i.toString())
        }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_days_dropdown, days)
        binding.time.setAdapter(arrayAdapter)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewOptions()
        with(panelViewModel) {
            panelPageViewStateLiveData.observeNonNull(viewLifecycleOwner) {
                with(binding) {
                    pageViewState = it
                    executePendingBindings()
                }
            }
        }
    }

    private fun handleViewOptions() {
        with(binding) {
            interestRate.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        interestRate.setText("0")
                    } else if (s.toString().toDouble() > 100) {
                        interestRate.setText("100")
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })

            calculateButton.setOnClickListener {
                val inputMethodManager = requireActivity().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
                if (amount.text.isNullOrEmpty() || interestRate.text.isNullOrEmpty() || time.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    val amount = amount.text.toString().replace(".", "").toInt()
                    val interestRate = interestRate.text.toString().toInt()
                    val days = time.text.toString().toInt()
                    panelViewModel.calculateInterest(days, amount, interestRate)
                }
            }
        }
    }
}