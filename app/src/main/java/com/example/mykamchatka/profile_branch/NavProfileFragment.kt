package com.example.mykamchatka.profile_branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykamchatka.R
import com.example.mykamchatka.adapters.SettingsAdapter
import com.example.mykamchatka.data_classes.SettingItem
import com.example.mykamchatka.databinding.FragmentProfileNavMenuBinding


class NavProfileFragment : Fragment() {

    private var _binding: FragmentProfileNavMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileNavMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myToursFragment = MyToursFragment.newInstance()
        val myTicketsFragment = MyTicketsFragment.newInstance()
        val myHotelsFragment = MyHotelsFragment.newInstance()


        // Список настроек
        val settingsItems = listOf(
            SettingItem(R.drawable.ic_my_tours, "Мои туры") {
                addFragment(myToursFragment)
            },
            SettingItem(R.drawable.ic_my_flights, "Мои билеты") {
                addFragment(myTicketsFragment)
            },
            SettingItem(R.drawable.ic_my_hotels, "Мои отели") {
                addFragment(myHotelsFragment)
            },
            SettingItem(R.drawable.ic_theme, "Тема") {
                // Обработчик для "Тема"
            },
            SettingItem(R.drawable.ic_notifications, "Уведомления") {
                // Обработчик для "Уведомления"
            },
            SettingItem(R.drawable.ic_role, "Поменять роль") {
                // Обработчик для "Поменять роль"
            },
            SettingItem(R.drawable.ic_help, "Помощь") {
                // Обработчик для "Помощь"
            }
        )

        // Настройка RecyclerView и Adapter
        binding.settingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.settingsRecyclerView.adapter = SettingsAdapter(settingsItems)

    }

    private fun addFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            add(R.id.flFragment, fragment) // Добавляет фрагмент, не заменяя основной (наслаиваем)
            addToBackStack(null) // Добавляет в back stack, чтобы вернуться назад при нажатии кнопки "Назад"
            commit()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = NavProfileFragment()
    }
}