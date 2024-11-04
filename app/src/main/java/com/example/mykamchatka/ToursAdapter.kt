package com.example.mykamchatka

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ToursAdapter(private var mTours: List<Tours>) : RecyclerView.Adapter<ToursAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitleTours)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDateTours)
        val durationTextView: TextView = itemView.findViewById(R.id.tvDurationTours)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPriceTours)
        val imageToursView: ImageView = itemView.findViewById(R.id.ivTours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_tour, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val news: Tours = mTours[position]
        viewHolder.nameTextView.text = news.name
        viewHolder.dateTextView.text = news.date
        viewHolder.durationTextView.text = news.duration
        viewHolder.priceTextView.text = news.price


        Glide.with(viewHolder.itemView.context)
            .load(news.imageUrl) // Загрузка изображения по ссылке
            .into(viewHolder.imageToursView) // Установка изображения в ImageView
    }

    override fun getItemCount(): Int {
        return mTours.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Tours>) {
        mTours = newData
        notifyDataSetChanged()
    }
}
