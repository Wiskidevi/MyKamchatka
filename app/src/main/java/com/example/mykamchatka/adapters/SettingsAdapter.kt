package com.example.mykamchatka.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykamchatka.R
import com.example.mykamchatka.data_classes.SettingItem

class SettingsAdapter (private val items: List<SettingItem>) :
    RecyclerView.Adapter<SettingsAdapter.SettingViewHolder>() {

    inner class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)

        fun bind(settingItem: SettingItem) {
            iconImageView.setImageResource(settingItem.iconResId)
            titleTextView.text = settingItem.title
            itemView.setOnClickListener {
                Log.d("SettingsAdapter", "Clicked on: ${settingItem.title}")
                settingItem.onClick?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return SettingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}