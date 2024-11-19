package com.example.mykamchatka.tours_branch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.DataModel
import com.example.mykamchatka.R
import com.example.mykamchatka.supabase.SupabaseHelper
import com.example.mykamchatka.adapters.ToursAdapter
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentToursNavMenuBinding
import kotlinx.coroutines.launch


class NavToursFragment : Fragment() {

    private var _binding: FragmentToursNavMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var toursAdapter: ToursAdapter
    private val dataModel: DataModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToursNavMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shoppingCartFragment = ShoppingCartFragment()


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
            lifecycleScope.launch {
                var listToDB: MutableList<Tours> = mutableListOf()

                val emptyList: List<Tours> = emptyList()

                val supabaseHelper = SupabaseHelper()
                val toursFromDB = supabaseHelper.getData<Tours>("ShoppingCartTours")
                val dataModelTours = dataModel.cartItems.value.orEmpty()
                if (toursFromDB != emptyList) {
                    for (i in 1..dataModelTours.size) {
                        if (!toursFromDB.contains(dataModelTours[i - 1])) {
                            listToDB.add(dataModelTours[i - 1])
                        }
                    }

                    saveCartToDatabase(listToDB)
                }
                else
                    saveCartToDatabase(dataModelTours)

                addFragment(shoppingCartFragment)
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

    // Добавление тура в cartItems
    private fun addToCart(tour: Tours) {
        val currentList = dataModel.cartItems.value.orEmpty() // Текущий список или пустой
        val updatedList = currentList.toMutableList()

        // Проверяем, существует ли тур с таким же именем
        if (currentList.none { it.name == tour.name }) {
            updatedList.add(tour) // Добавляем тур только если его нет
            dataModel.cartItems.value = updatedList // Обновляем LiveData
            Log.e("current, updated", listOf(currentList, updatedList).toString())
        }
    }



    private fun saveCartToDatabase(cartItems: List<Tours>) {
        lifecycleScope.launch {
            try {
                val supabaseHelper = SupabaseHelper()
                supabaseHelper.setData("ShoppingCartTours", cartItems)
                Log.d("Supabase shopping cart", "Tours successfully saved to database")
            } catch (e: Exception) {
                Log.e("Supabase shopping cart", "Error saving tours to database: ${e.message}")
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NavToursFragment()
    }


}