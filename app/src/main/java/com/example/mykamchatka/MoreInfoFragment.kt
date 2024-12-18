package com.example.mykamchatka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentMoreInfoBinding
import com.example.mykamchatka.databinding.FragmentProfileNavMenuBinding
import com.google.android.material.imageview.ShapeableImageView

class MoreInfoFragment : Fragment() {

    private var _binding: FragmentMoreInfoBinding? = null
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
        _binding = FragmentMoreInfoBinding.inflate(inflater, container, false)

        // Установка данных в элементы интерфейса
        selectedTour?.let { tour ->
            binding.tvHeaderItem.text = tour.name
            binding.tvPrice.text = tour.price
            binding.tvDuration.text = tour.duration
            binding.tvDate.text = tour.date
            binding.tvWhatTakeDesc.text = tour.things_to_bag

            binding.ivMoreInfo.apply {
                val firstImgUrl = tour.imageUrl.firstOrNull()
                Glide.with(this).load(firstImgUrl).into(this)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnBooking.setOnClickListener {
            selectedTour?.let { tour ->
                val bookingEnterInformationFragment = BookingEnterInformationFragment.newInstance(tour)
                addFragment(bookingEnterInformationFragment)
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
        fun newInstance(tour: Tours): MoreInfoFragment {
            val fragment = MoreInfoFragment()
            val args = Bundle().apply {
                putParcelable("selectedTour", tour)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
