package com.hrudhaykanth116.weathertens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.databinding.ActivityMainBinding
import com.hrudhaykanth116.weathertens.features.auth.data.datasources.remote.IAuthRemoteDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseIAuthRemoteDataSource: IAuthRemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Since custom splash screen are not recommended, work around
        lifecycleScope.launch {
            when (firebaseIAuthRemoteDataSource.getLoggedInUserId()) {
                is DataResult.Error -> {
                    findNavController(R.id.nav_host_fragment).setGraph(R.navigation.auth_nav_graph)
                }
                is DataResult.Success -> {
                    findNavController(R.id.nav_host_fragment).setGraph(R.navigation.weather_nav_graph)
                }
            }
            // TODO: Implement splash screen api
            // binding.splashScreen.isVisible = false
        }

    }

}