package com.example.mykamchatka

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentBookingBillBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BookingBillFragment : Fragment() {

    private var _binding: FragmentBookingBillBinding? = null
    private val binding get() = _binding!!

    private var selectedTour: Tours? = null
    private var userInputFCs: String? = null
    private var userInputCount: String? = null
    private var userInputPhoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedTour = it.getParcelable("selectedTour")
            userInputFCs = it.getString("userInputFCs")
            userInputCount = it.getString("userInputCount")
            userInputPhoneNumber = it.getString("userInputPhoneNumber")
        }
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBillBinding.inflate(inflater, container, false)

        selectedTour?.let { tour ->
            binding.tvHeaderItem.text = tour.name
            binding.tvDuration.text = tour.duration
            binding.tvDate.text = tour.date
            binding.tvServiceNameCard.text = "Тур " + tour.name
            binding.tvServicePriceCard.text = tour.price

            binding.ivMoreInfo.apply {
                val firstImgUrl = tour.imageUrl.firstOrNull()
                Glide.with(this).load(firstImgUrl).into(this)
            }
        }

        binding.tvClientNameCard.text = userInputFCs
        binding.tvServiceCountCard.text = "$userInputCount шт."

            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookingIssuedFragment = BookingIssuedFragment()

        binding.btnPay.setOnClickListener(){
            selectedTour?.let {
                saveTourLocally(it)
                addFragment(bookingIssuedFragment)
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun saveTourLocally(tour: Tours) {
        val sharedPreferences = requireContext().getSharedPreferences("MyToursPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Получение существующих данных (если есть)
        val existingData = sharedPreferences.getString("savedTours", null)
        val tourList: MutableList<Tours> = if (!existingData.isNullOrEmpty()) {
            Gson().fromJson(existingData, object : TypeToken<MutableList<Tours>>() {}.type)
        } else {
            mutableListOf()
        }

        // Добавление нового тура в список
        tourList.add(tour)

        // Сохранение обновлённого списка
        val updatedData = Gson().toJson(tourList)
        editor.putString("savedTours", updatedData)
        editor.apply()
    }


    companion object {
        fun newInstance(tour: Tours, userInputFCs: String, userInputCount: String, userInputPhoneNumber: String): BookingBillFragment {
            val fragment = BookingBillFragment()
            val args = Bundle().apply {
                putParcelable("selectedTour", tour)
                putString("userInputFCs", userInputFCs)
                putString("userInputCount", userInputCount)
                putString("userInputPhoneNumber", userInputPhoneNumber)
            }
            fragment.arguments = args
            return fragment
        }
    }

}