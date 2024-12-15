package com.example.mykamchatka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mykamchatka.databinding.FragmentBookingIssuedBinding
import com.example.mykamchatka.profile_branch.MyToursFragment
import com.example.mykamchatka.tours_branch.NavToursFragment


class BookingIssuedFragment : Fragment() {

    private var _binding: FragmentBookingIssuedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingIssuedBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navToursFragment = NavToursFragment()
        val myToursFragment = MyToursFragment()

        binding.btnDone.setOnClickListener(){
            setFragment(navToursFragment)
        }

        binding.btnToMyBooking.setOnClickListener(){
            setFragment(myToursFragment)
        }
    }

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = BookingIssuedFragment()
    }
}