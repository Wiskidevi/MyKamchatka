package com.example.mykamchatka


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.databinding.ActivityChoiseRegEntBinding

class ChoiseRegEntActivity : AppCompatActivity() {

    private var _binding : ActivityChoiseRegEntBinding? = null
    private val binding
        get()=_binding ?: throw IllegalStateException("Binding for ActivityChoise must not be null!")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChoiseRegEntBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegistration.setOnClickListener{
            val intent = Intent(this@ChoiseRegEntActivity, RegistrationActivity::class.java)
                startActivity(intent)
        }

        binding.btnEntrance.setOnClickListener{
            val intent = Intent(this@ChoiseRegEntActivity, EntranceActivity::class.java)
            startActivity(intent)
        }
    }

    
}