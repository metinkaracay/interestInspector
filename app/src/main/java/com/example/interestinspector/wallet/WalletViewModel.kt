package com.example.interestinspector.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletViewModel: ViewModel() {
    private val _walletViewStateLiveData: MutableLiveData<WalletViewState> = MutableLiveData()
    val walletViewStateLiveData: LiveData<WalletViewState> = _walletViewStateLiveData

    init {
        _walletViewStateLiveData.value = WalletViewState()
    }
}