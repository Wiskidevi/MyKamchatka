package com.example.mykamchatka.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.supabase.DbHelper
import com.example.mykamchatka.MainActivity
import com.example.mykamchatka.R
import com.example.mykamchatka.supabase.User
import com.example.mykamchatka.databinding.ActivityOperatorRegCategoryBinding

class OperatorRegCategoryActivity : AppCompatActivity() {

    private var _binding: ActivityOperatorRegCategoryBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding for PersonalDataActivity must not be null!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOperatorRegCategoryBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnNext.setOnClickListener {
            val bundleReceived = intent.getBundleExtra("BUNDLE")
            val email = bundleReceived?.getString(RegistrationActivity.EMAIL) ?: ""
            val pass = bundleReceived?.getString(RegistrationActivity.PASSWORD) ?: ""
            val surname = bundleReceived?.getString(PersonalDataActivity.SURNAME) ?: ""
            val name = bundleReceived?.getString(PersonalDataActivity.NAME) ?: ""
            val thirdname = bundleReceived?.getString(PersonalDataActivity.THIRDNAME) ?: ""
            val birthday = bundleReceived?.getString(PersonalDataActivity.BIRTHDAY) ?: ""

            val role = "Туристическая компания"

            val user = User(email, pass, surname, name, thirdname, birthday, role)

            val db = DbHelper(this, null)
            db.addUser(user)
            Toast.makeText(this, "Компания добавлена", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@OperatorRegCategoryActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}