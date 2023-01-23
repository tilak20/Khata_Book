package com.example.sqlite_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite_db.Adapter.Bottom_Nav_Adapter
import com.example.sqlite_db.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Adapter()

        binding.TabBar.addTab(binding.TabBar.newTab().setIcon(R.drawable.user))
        binding.TabBar.addTab(binding.TabBar.newTab().setIcon(R.drawable.rupee3))
        binding.TabBar.addTab(binding.TabBar.newTab().setIcon(R.drawable.calculator1))
        binding.TabBar.addTab(binding.TabBar.newTab().setIcon(R.drawable.user_profile))
    }

    fun Adapter() {
        val bottom_Nav_Adapter = Bottom_Nav_Adapter(supportFragmentManager)
        binding.viewPagerScreen.adapter = bottom_Nav_Adapter
        binding.viewPagerScreen.setOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.TabBar
            )
        )

        binding.TabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPagerScreen.currentItem =tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}