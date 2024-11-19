package com.example.mykamchatka.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mykamchatka.R
import com.example.mykamchatka.data_classes.Tours


class CartAdapter(
    private var cartItems: List<Tours>)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitleTours)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDateTours)
        val durationTextView: TextView = itemView.findViewById(R.id.tvDurationTours)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPriceTours)
        val imageToursView: ImageView = itemView.findViewById(R.id.ivTours)
        val btnBuyTour: ImageButton = itemView.findViewById(R.id.btnBuyTour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_tour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tour = cartItems[position]

        viewHolder.nameTextView.text = tour.name
        viewHolder.dateTextView.text = tour.date
        viewHolder.durationTextView.text = tour.duration
        viewHolder.priceTextView.text = tour.price
        viewHolder.btnBuyTour.setImageResource(R.drawable.ic_shopping_card_add)

        Glide.with(viewHolder.itemView.context)
            .load(tour.imageUrl)
            .into(viewHolder.imageToursView)

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Tours>) {
        cartItems = newData
        notifyDataSetChanged()
    }
}
