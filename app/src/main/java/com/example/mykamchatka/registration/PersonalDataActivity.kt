package com.example.mykamchatka.registration

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykamchatka.R
import com.example.mykamchatka.databinding.ActivityPersonalDataBinding

class PersonalDataActivity : AppCompatActivity() {

    private var _binding: ActivityPersonalDataBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding for PersonalDataActivity must not be null!")
    private var userBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPersonalDataBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root)
        enableEdgeToEdge()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnNext.setOnClickListener{
            val surname = binding.userSurname.text.toString().trim()
            val name = binding.userName.text.toString().trim()
            val thirdname = binding.userThirdname.text.toString().trim()
            val birthday = binding.userBirthdayDate.text.toString().trim()

            userBundle.putString(SURNAME, surname)
            userBundle.putString(NAME, name)
            userBundle.putString(THIRDNAME, thirdname)
            userBundle.putString(BIRTHDAY, birthday)


            val intent = Intent(this@PersonalDataActivity, PersonalData2Activity::class.java)
            intent.putExtra("BUNDLE", userBundle)

            startActivity(intent)
        }
    }

    companion object {
        const val SURNAME = "SURNAME"
        const val NAME = "NAME"
        const val THIRDNAME = "THIRDNAME"
        const val BIRTHDAY = "BIRTHDAY"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // Avoid memory leaks
    }
}