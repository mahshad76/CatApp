package com.mahshad.catapp.ui.viewpager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mahshad.catapp.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var viewpagerAdapter: ViewPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        viewpagerAdapter = ViewPagerAdaptor(
            listOf(DetailedDescriptionFragment(), SellerInfoFragment()),
            supportFragmentManager,
            lifecycle
        )

        viewPager.adapter = viewpagerAdapter

        //TabLayoutMediator: This class is specifically designed to connect a TabLayout with a ViewPager2.
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Detailed Description"
                1 -> "Seller Info"
                else -> "Detailed Description"
            }
        }.attach()

    }
}