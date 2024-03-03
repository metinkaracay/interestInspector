package com.example.interestinspector

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import common.SingleLiveEvent
import common.navigation.NavigationData

class MainViewModel: ViewModel() {

    private val _navigateToDestinationComponentSingleLiveEvent: SingleLiveEvent<NavigationData> = SingleLiveEvent()
    val navigateToDestinationComponentSingleLiveEvent: LiveData<NavigationData> = _navigateToDestinationComponentSingleLiveEvent

    fun onFirstFragmentButtonClick() {
        _navigateToDestinationComponentSingleLiveEvent.value = NavigationData(R.id.firstFragment)
    }

    fun onSecondFragmentButtonClick() {
        _navigateToDestinationComponentSingleLiveEvent.value = NavigationData(R.id.secondFragment)
    }
}