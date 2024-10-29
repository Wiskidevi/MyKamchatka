package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.databinding.FragmentNewsTabAllBinding

class AllNewsTabFragment : Fragment() {

    private var _binding: FragmentNewsTabAllBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels()

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

        // Настройка RecyclerView
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за данными из ViewModel
        newsViewModel.fetchNews()
        newsViewModel.newsList.observe(viewLifecycleOwner, Observer { newsList ->
            binding.recyclerViewNews.adapter = NewsAdapter(newsList)
        })
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
