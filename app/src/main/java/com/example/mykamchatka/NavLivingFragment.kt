package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.databinding.FragmentLivingNavMenuBinding
import kotlinx.coroutines.launch

class NavLivingFragment : Fragment() {

    private var _binding: FragmentLivingNavMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var livingAdapter: LivingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLivingNavMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем RecyclerView и адаптер
        // Выводим данные о новостях из supabase
        livingAdapter = LivingAdapter(emptyList())
        binding.rvLiving.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = livingAdapter
        }

        // Показываем анимацию загрузки
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.rvLiving.visibility = View.GONE
        binding.btnFilterLiving.visibility = View.GONE

        val db = SupabaseHelper()

        lifecycleScope.launch {
            val data = db.getData<Living>("LivingTable")
            livingAdapter.updateData(data)

            // Скрываем анимацию загрузки и показываем RecyclerView после загрузки данных
            binding.loadingAnimation.visibility = View.GONE
            binding.rvLiving.visibility = View.VISIBLE
            binding.btnFilterLiving.visibility = View.VISIBLE
            return@launch
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = NavLivingFragment()
    }
}