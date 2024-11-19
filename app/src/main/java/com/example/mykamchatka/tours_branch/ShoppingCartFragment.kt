package com.example.mykamchatka.tours_branch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.adapters.CartAdapter
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentShoppingCartBinding
import com.example.mykamchatka.supabase.SupabaseHelper
import kotlinx.coroutines.launch


class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(emptyList())

        binding.rvCart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        // Показываем анимацию загрузки
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.rvCart.visibility = View.GONE

        val db = SupabaseHelper()

        lifecycleScope.launch {
            val data = db.getData<Tours>("ShoppingCartTours")
            cartAdapter.updateData(data)

            binding.loadingAnimation.visibility = View.GONE
            binding.rvCart.visibility = View.VISIBLE
            return@launch
        }

    }


    companion object{
        @JvmStatic
        fun newInstance() = ShoppingCartFragment()
    }
}