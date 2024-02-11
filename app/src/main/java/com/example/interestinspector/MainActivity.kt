package com.example.interestinspector

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.interestinspector.databinding.ActivityMainBinding
import common.navigation.NavigationData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    private val mainViewModel: MainViewModel by viewModels()

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainViewModel.navigateToDestinationComponentSingleLiveEvent.observe(this@MainActivity) {
            navigateToDestination(navController = navController, navigationData = it)
        }

        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    fun navigateToDestination(navController: NavController, navigationData: NavigationData) {
        navController.navigate(navigationData.destinationId)
    }
}