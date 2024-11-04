package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.databinding.FragmentToursNavMenuBinding
import kotlinx.coroutines.launch


class NavToursFragment : Fragment() {

    private var _binding: FragmentToursNavMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var toursAdapter: ToursAdapter

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

        //val navToursListFragment = ToursListFragment.newInstance()
        //setCurrentFragment(navToursListFragment)

        super.onViewCreated(view, savedInstanceState)

        toursAdapter = ToursAdapter(emptyList())
        binding.rvTours.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = toursAdapter
        }

        // Показываем анимацию загрузки
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.rvTours.visibility = View.GONE

        val db = SupabaseHelper()

        lifecycleScope.launch {
            val data = db.getData<Tours>("ToursTable")
            toursAdapter.updateData(data)

            binding.loadingAnimation.visibility = View.GONE
            binding.rvTours.visibility = View.VISIBLE
            return@launch
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NavToursFragment()
    }

//    private fun setCurrentFragment(fragment: Fragment) =
//        parentFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragmentToursList, fragment)
//            commit()
//        }

}