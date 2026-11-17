package com.hantech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            topAppBar.title = "Han-Tech Ltd"
        }

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> { topAppBar.title = "Han-Tech Ltd"; HomeFragment() }
                R.id.nav_services -> { topAppBar.title = "Our Services"; ServicesFragment() }
                R.id.nav_feedback -> { topAppBar.title = "Feedback"; FeedbackFragment() }
                R.id.nav_profile -> { topAppBar.title = "My Profile"; ProfileFragment() }
                R.id.nav_settings -> { topAppBar.title = "Settings"; SettingsFragment() }
                else -> return@setOnItemSelectedListener false
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            true
        }
    }
}