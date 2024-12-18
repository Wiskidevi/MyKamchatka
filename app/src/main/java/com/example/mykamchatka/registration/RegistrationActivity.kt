package com.example.mykamchatka.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.R
import com.example.mykamchatka.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private var _binding: ActivityRegistrationBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityRegistration must not be null!")
    private var userBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegistrationBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnNext.setOnClickListener{
            val email = binding.userEmail.text.toString().trim()
            val pass = binding.userPass.text.toString().trim()
            val passConfirm = binding.userPassConfirm.text.toString().trim()

            if(email == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            else if(pass != passConfirm)
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            else{
                userBundle.putString(EMAIL, email)
                userBundle.putString(PASSWORD, pass)

                Log.d("USER USER", "${userBundle.getString("EMAIL")}")

                val intent = Intent(this@RegistrationActivity, PersonalDataActivity::class.java)
                intent.putExtra("BUNDLE", userBundle)
                startActivity(intent)

            }



        }
    }

    companion object {
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASSWORD"
    }


}