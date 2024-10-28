package com.example.mykamchatka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.example.mykamchatka.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityRegistration must not be null!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root)
        enableEdgeToEdge()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navNewsFragment = NavNewsFragment.newInstance()
        val navToursFragment = NavToursFragment.newInstance()
        val navLivingFragment = NavLivingFragment.newInstance()
        val navProfileFragment = NavProfileFragment.newInstance()

        setCurrentFragment(navNewsFragment)
        binding.bottomNavigationView.selectedItemId = R.id.news

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
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


        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNavigationView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = 0) // Устанавливаем нижний padding в 0
            insets
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

}