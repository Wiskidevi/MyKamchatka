package com.example.mykamchatka.tours_branch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.R
import com.example.mykamchatka.adapters.CartAdapter
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentShoppingCartBinding


class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    private val cartItems = mutableListOf<Tours>()
    private lateinit var cartAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(cartItems)
        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addTourToCart(tour: Tours, cartAdapter: CartAdapter) {
        cartItems.add(tour)
        cartAdapter.notifyDataSetChanged()
    }

    companion object{
        @JvmStatic
        fun newInstance() = ShoppingCartFragment()
    }
}