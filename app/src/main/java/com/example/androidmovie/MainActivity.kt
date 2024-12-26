package com.example.androidmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.core.navigation.NavigationFlow
import com.example.core.navigation.Navigator
import com.example.core.navigation.ToFlowNavigatable
import com.example.core.navigation.R.navigation.main_nav_graph


class MainActivity : AppCompatActivity(R.layout.activity_main), ToFlowNavigatable {

    private val navigator: Navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(main_nav_graph)
        navigator.navController = navHostFragment.navController
        navigator.navController.graph = graph
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}
