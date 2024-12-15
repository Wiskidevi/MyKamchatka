package com.example.mykamchatka.profile_branch

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.R
import com.example.mykamchatka.adapters.ToursAdapter
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentMyToursBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MyToursFragment : Fragment() {

    private var _binding: FragmentMyToursBinding? = null
    private val binding get() = _binding!!

    private fun loadTours(): List<Tours> {
        val sharedPreferences = requireContext().getSharedPreferences("MyToursPrefs", Context.MODE_PRIVATE)
        val savedData = sharedPreferences.getString("savedTours", null)

        return if (!savedData.isNullOrEmpty()) {
            Gson().fromJson(savedData, object : TypeToken<List<Tours>>() {}.type)
        } else {
            emptyList()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyToursBinding.inflate(inflater, container, false)

        // Загрузка данных из SharedPreferences
        val savedTours = loadTours()


        // Инициализация адаптера
        val toursAdapter = ToursAdapter(
            mTours = savedTours,
            onAddToCartClicked = { tour -> },
            onMoreInfoClicked = { tour ->

            }
        )


        binding.rvMyTours.apply {
            adapter = toursAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = MyToursFragment()
    }
}