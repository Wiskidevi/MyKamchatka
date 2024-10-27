package com.example.mykamchatka

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.databinding.ActivityPersonalData2Binding
import com.example.mykamchatka.databinding.ActivityPersonalDataBinding

class PersonalData2Activity : AppCompatActivity() {

    private var _binding: ActivityPersonalData2Binding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding for PersonalData2Activity must not be null!")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPersonalData2Binding.inflate(layoutInflater) // Inflate the binding
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
            val selectedId = binding.radioGroup.checkedRadioButtonId

            when (selectedId) {
                binding.rbTourist.id -> {

                    val role = binding.rbTourist.text.toString()

                    val user = User(email, pass, surname, name, thirdname, birthday, role)

                    val db = DbHelper(this, null)
                    db.addUser(user)
                    Toast.makeText(this, "Пользователь добавлен", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@PersonalData2Activity, TouristRegOkActivity::class.java)
                    startActivity(intent)
                }
                binding.rbCompany.id -> {
                    val intent = Intent(this, OperatorRegOkActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Роль не выбрана", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // Avoid memory leaks
    }
}