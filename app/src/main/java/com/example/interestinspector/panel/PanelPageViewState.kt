package com.example.interestinspector.panel

import android.view.View

data class PanelPageViewState(
    val amount: String? = null,
    val grossIncome: String? = null,
    val deduction: String? = null,
    val net: String? = null,
    val totalAmount: String? = null
) {
    fun timeHintText() = "Gün"
    fun amountHintText() = "Tutar"
    fun interestRateHintText() = "Faiz Oranı"
    fun calculateButtonText() = "Hesapla"
    fun resultVisibility() = if (net != null) View.VISIBLE else View.GONE
    fun grossIncomeText() = "Kazancınız: $grossIncome ₺"
    fun deductionText() = "Kesinti: $deduction ₺"
    fun netIncomeText() = "Net Kazancınız: $net ₺"
    fun totalAmountText() = "Vade Sonu: $totalAmount ₺"
}