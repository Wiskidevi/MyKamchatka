package com.example.mykamchatka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mykamchatka.databinding.ActivityMainBinding
import com.example.mykamchatka.living_branch.NavLivingFragment
import com.example.mykamchatka.news_branch.NavNewsFragment
import com.example.mykamchatka.profile_branch.NavProfileFragment
import com.example.mykamchatka.tours_branch.NavToursFragment


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityRegistration must not be null!")

    // использование DataModel


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
            addToBackStack(null)
            commit()
        }


    fun deleteFragment(layout : Int) {
        supportFragmentManager
            .beginTransaction().apply {
                supportFragmentManager
                    .findFragmentById(layout)?.let {
                    remove(it)
                }
                commit()
            }
    }
}