package com.application.snapcal.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.snapcal.R
import com.application.snapcal.databinding.ActivityMainBinding
import com.application.snapcal.view.homeFragment.HomeFragment
import com.application.snapcal.view.ViewModelFactory
import com.application.snapcal.view.cameraX.CameraXActivity
import com.application.snapcal.view.profileFragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.home
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
        binding.fabCameraX.setOnClickListener {
            val intent = Intent(this, CameraXActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "CameraX", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}