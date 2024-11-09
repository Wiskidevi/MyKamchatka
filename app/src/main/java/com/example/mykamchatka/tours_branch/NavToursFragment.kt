package com.example.mykamchatka.tours_branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.R
import com.example.mykamchatka.supabase.SupabaseHelper
import com.example.mykamchatka.adapters.CartAdapter
import com.example.mykamchatka.adapters.ToursAdapter
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentToursNavMenuBinding
import kotlinx.coroutines.launch


class NavToursFragment : Fragment() {

    private var _binding: FragmentToursNavMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var toursAdapter: ToursAdapter
    private lateinit var shoppingCartFragment: ShoppingCartFragment
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToursNavMenuBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(emptyList())

        toursAdapter = ToursAdapter(emptyList()) { tour ->
            addToCart(tour) // Обработка добавления в корзину
        }
        binding.rvTours.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = toursAdapter
        }

        // Показываем анимацию загрузки
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.rvTours.visibility = View.GONE
        binding.btnFilterTour.visibility = View.GONE

        val db = SupabaseHelper()

        lifecycleScope.launch {
            val data = db.getData<Tours>("ToursTable")
            toursAdapter.updateData(data)

            binding.loadingAnimation.visibility = View.GONE
            binding.rvTours.visibility = View.VISIBLE
            binding.btnFilterTour.visibility = View.VISIBLE
            return@launch
        }



        binding.ibShoppingCart.setOnClickListener {
            addFragment(shoppingCartFragment)
        }



    }

    private fun addFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.flFragment, fragment) // Добавляет фрагмент, не заменяя основной (наслаиваем)
            addToBackStack(null) // Добавляет в back stack, чтобы вернуться назад при нажатии кнопки "Назад"
            commit()
        }
    }

    // Функция добавления тура в корзину
    private fun addToCart(tour: Tours) {
        val shoppingCartFragment = parentFragmentManager.findFragmentByTag("ShoppingCartFragment") as? ShoppingCartFragment
            ?: ShoppingCartFragment.newInstance()

        // Передаем тур в корзину
        shoppingCartFragment.addTourToCart(tour, cartAdapter)

        // Показываем фрагмент корзины, если его еще нет
        if (!shoppingCartFragment.isAdded) {
            addFragment(shoppingCartFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NavToursFragment()
    }


}