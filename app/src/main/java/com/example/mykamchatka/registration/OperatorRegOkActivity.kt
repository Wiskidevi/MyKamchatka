package com.example.mykamchatka.registration

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.MainActivity
import com.example.mykamchatka.R
import com.example.mykamchatka.databinding.ActivityOperatorRegOkBinding

class OperatorRegOkActivity : AppCompatActivity() {

    private var _binding: ActivityOperatorRegOkBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding for PersonalDataActivity must not be null!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOperatorRegOkBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@OperatorRegOkActivity, OperatorRegCategoryActivity::class.java)
            startActivity(intent)
        }

        binding.btnLater.setOnClickListener {
            val intent = Intent(this@OperatorRegOkActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}