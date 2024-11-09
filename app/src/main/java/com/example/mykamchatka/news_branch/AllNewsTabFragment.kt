package com.example.mykamchatka.news_branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.supabase.SupabaseHelper
import com.example.mykamchatka.adapters.NewsAdapter
import com.example.mykamchatka.data_classes.News
import com.example.mykamchatka.databinding.FragmentNewsTabAllBinding
import kotlinx.coroutines.launch



class AllNewsTabFragment : Fragment() {

    private var _binding: FragmentNewsTabAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsTabAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем RecyclerView и адаптер
        // Выводим данные о новостях из supabase
        newsAdapter = NewsAdapter(emptyList())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        // Показываем анимацию загрузки
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE

        val db = SupabaseHelper()

        lifecycleScope.launch {
            val data = db.getData<News>("NewsTable")
            newsAdapter.updateData(data)

            // Скрываем анимацию загрузки и показываем RecyclerView после загрузки данных
            binding.loadingAnimation.visibility = View.GONE
            binding.rvNews.visibility = View.VISIBLE
            return@launch
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllNewsTabFragment()
    }
}
