package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class NavToursFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tours_nav_menu, container, false)
    }

    companion object{
        @JvmStatic
        fun newInstance() = NavToursFragment()
    }
}