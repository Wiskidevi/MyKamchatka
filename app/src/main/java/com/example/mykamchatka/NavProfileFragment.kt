package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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


        // Список настроек
        val settingsItems = listOf(
            SettingItem(R.drawable.ic_my_tours, "Мои туры") {
                // Обработчик для "Мои туры"
            },
            SettingItem(R.drawable.ic_my_flights, "Мои билеты") {
                // Обработчик для "Мои билеты"
            },
            SettingItem(R.drawable.ic_my_hotels, "Мои отели") {
                // Обработчик для "Мои отели"
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

    companion object{
        @JvmStatic
        fun newInstance() = NavProfileFragment()
    }
}