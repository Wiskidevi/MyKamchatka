package com.example.mykamchatka

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LivingAdapter(private var mLiving: List<Living>) : RecyclerView.Adapter<LivingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitleLiving)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDateLiving)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPriceLiving)
        val imageLivingView: ImageView = itemView.findViewById(R.id.ivLiving)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_living, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val living: Living = mLiving[position]
        viewHolder.nameTextView.text = living.name
        viewHolder.dateTextView.text = living.date
        viewHolder.priceTextView.text = living.price

        Glide.with(viewHolder.itemView.context)
            .load(living.image) // Загрузка изображения по ссылке
            .into(viewHolder.imageLivingView) // Установка изображения в ImageView
    }

    override fun getItemCount(): Int {
        return mLiving.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Living>) {
        mLiving = newData
        notifyDataSetChanged()
    }
}
