package com.example.interestinspector.panel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PanelViewModel: ViewModel() {

    private val _panelPageViewStateLiveData: MutableLiveData<PanelPageViewState> = MutableLiveData()
    val panelPageViewStateLiveData: LiveData<PanelPageViewState> = _panelPageViewStateLiveData

    init {
        _panelPageViewStateLiveData.value = PanelPageViewState()
    }

    fun calculateInterest(days: Int, amount: Int, interestRate: Int) {
        val grossIncome = (amount * interestRate * days / 36600).toDouble()
        val deductionRate = when(days) {
            in 1..180 -> 5
            in 181..365 -> 3
            else -> 0
        }
        val deduction = grossIncome * deductionRate / 100
        val netIncome = grossIncome - deduction
        val totalAmount = amount + netIncome
        _panelPageViewStateLiveData.value = panelPageViewStateLiveData.value?.copy(net = netIncome.toString(), deduction = deduction.toString(), grossIncome = grossIncome.toString(), amount = amount.toString(), totalAmount = totalAmount.toString())
    }
}