package com.example.mykamchatka.news_branch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mykamchatka.R

class HistoryNewsTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_tab_history, container, false)
    }

    companion object{
        @JvmStatic
        fun newInstance() = HistoryNewsTabFragment()
    }
}