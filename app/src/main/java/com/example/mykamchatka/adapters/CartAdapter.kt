package com.example.mykamchatka.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykamchatka.R
import com.example.mykamchatka.data_classes.Tours


class CartAdapter(private val cartItems: List<Tours>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitleTours)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPriceTours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_tour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tour = cartItems[position]
        holder.nameTextView.text = tour.name
        holder.priceTextView.text = tour.price
    }



    override fun getItemCount(): Int {
        return cartItems.size
    }
}
