package com.example.mykamchatka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentBookingEnterInformationBinding


class BookingEnterInformationFragment : Fragment() {

    private var _binding: FragmentBookingEnterInformationBinding? = null
    private val binding get() = _binding!!

    private var selectedTour: Tours? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedTour = it.getParcelable("selectedTour")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingEnterInformationBinding.inflate(inflater, container, false)

        selectedTour?.let { tour ->
            binding.tvHeaderItem.text = tour.name
            binding.tvDuration.text = tour.duration
            binding.tvDate.text = tour.date
            binding.ivMoreInfo.apply {
                val firstImgUrl = tour.imageUrl.firstOrNull()
                Glide.with(this).load(firstImgUrl).into(this)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnNextBooking.setOnClickListener {

            val userInputFCs = binding.tilUserFCs.editText?.text.toString()
            val userInputCount = binding.tilUserCount.editText?.text.toString()
            val userInputPhoneNumber = binding.tilUserPhone.editText?.text.toString()

            selectedTour?.let { tour ->
                val bookingBillFragment = BookingBillFragment.newInstance(tour, userInputFCs, userInputCount, userInputPhoneNumber)
                addFragment(bookingBillFragment)
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

    companion object {
        fun newInstance(tour: Tours): BookingEnterInformationFragment {
            val fragment = BookingEnterInformationFragment()
            val args = Bundle().apply {
                putParcelable("selectedTour", tour)
            }
            fragment.arguments = args
            return fragment
        }
    }
}