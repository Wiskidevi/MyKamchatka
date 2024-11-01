package com.example.mykamchatka

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private var mNews: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDate)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        val imageNewsView: ImageView = itemView.findViewById(R.id.ivNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_news, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val news: News = mNews[position]
        viewHolder.titleTextView.text = news.title
        viewHolder.dateTextView.text = news.date
        viewHolder.descriptionTextView.text = news.description

        Glide.with(viewHolder.itemView.context)
            .load(news.imageUrl) // Загрузка изображения по ссылке
            .into(viewHolder.imageNewsView) // Установка изображения в ImageView
    }

    override fun getItemCount(): Int {
        return mNews.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<News>) {
        mNews = newData
        notifyDataSetChanged()
    }
}
