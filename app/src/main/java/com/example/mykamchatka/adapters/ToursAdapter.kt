package com.example.mykamchatka.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mykamchatka.R
import com.example.mykamchatka.data_classes.Tours

class ToursAdapter(
    private var mTours: List<Tours>,
    private val onAddToCartClicked: (Tours) -> Unit,
    private val onMoreInfoClicked: (Tours) -> Unit
) : RecyclerView.Adapter<ToursAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTitleTours)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDateTours)
        val durationTextView: TextView = itemView.findViewById(R.id.tvDurationTours)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPriceTours)
        val imageToursView: ImageView = itemView.findViewById(R.id.ivTours)
        val btnBuyTour: ImageButton = itemView.findViewById(R.id.btnBuyTour)
        val btnMoreTour: Button = itemView.findViewById(R.id.btnMoreTour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_tour, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tour: Tours = mTours[position]
        viewHolder.nameTextView.text = tour.name
        viewHolder.dateTextView.text = tour.date
        viewHolder.durationTextView.text = tour.duration
        viewHolder.priceTextView.text = tour.price

        val firstImgUrl = tour.imageUrl.firstOrNull()

        if(firstImgUrl != null) {
            Glide.with(viewHolder.itemView.context)
                .load(firstImgUrl)
                .into(viewHolder.imageToursView)
        }
//        else{
//            Glide.with(viewHolder.itemView.context)
//            .load(R.drawable.placeholder_image) // Ваш placeholder
//            .into(viewHolder.imageToursView)
//        }
        // Обработка клика по кнопке "Добавить в корзину"
        viewHolder.btnBuyTour.setOnClickListener {
            onAddToCartClicked(tour) // Передача элемента в функцию
            viewHolder.btnBuyTour.setImageResource(R.drawable.ic_shopping_card_add)
            viewHolder.btnBuyTour.isEnabled=false
        }

        viewHolder.btnMoreTour.setOnClickListener {
            onMoreInfoClicked(tour)
        }
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
