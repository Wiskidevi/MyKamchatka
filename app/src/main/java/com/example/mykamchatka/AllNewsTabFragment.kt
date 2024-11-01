package com.example.mykamchatka

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.databinding.FragmentNewsTabAllBinding
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.launch
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.Serializable


@Serializable
data class News (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val article: String = "",
    val site: String = "",
    val news_type: String = "",
)


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
        newsAdapter = NewsAdapter(emptyList())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        getNewsData()

    }


    private fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://dghywtgmjhxooohbuthl.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRnaHl3dGdtamh4b29vaGJ1dGhsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjgzNDMxNjMsImV4cCI6MjA0MzkxOTE2M30.-lsBVoa8WnzFsRWTGi8oDYEqAP7KtSs3brt9jW_uKk0"
        ) {
            install(Postgrest)
        }
    }

    private fun getNewsData(){
        lifecycleScope.launch {
            val client = getClient()
            val supabaseResponse = client.postgrest["NewsTable"].select()
            val data = supabaseResponse.decodeList<News>()
            Log.e("supabase", data.toString())

            newsAdapter.updateData(data)
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
