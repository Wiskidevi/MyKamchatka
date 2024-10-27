package com.example.mykamchatka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navNewsFragment = NavNewsFragment()
        val navToursFragment = NavToursFragment()
        val navLivingFragment = NavLivingFragment()
        val navProfileFragment = NavProfileFragment()

        setCurrentFragment(navNewsFragment)
        bottomNavigationView.selectedItemId = R.id.news

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.living -> {
                    setCurrentFragment(navLivingFragment)
                }
                R.id.tours -> {
                    setCurrentFragment(navToursFragment)
                }
                R.id.news -> {
                    setCurrentFragment(navNewsFragment)
                }
                R.id.profile -> {
                    setCurrentFragment(navProfileFragment)
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

}