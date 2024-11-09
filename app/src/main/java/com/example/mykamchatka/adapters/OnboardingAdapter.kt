package com.example.mykamchatka.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val context: Context, private val layouts: IntArray) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(layouts[viewType], parent, false)
        return OnboardingViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        // настройка элементов экрана
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
