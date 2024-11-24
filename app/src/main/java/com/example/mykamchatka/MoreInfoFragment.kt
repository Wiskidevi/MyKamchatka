package com.example.mykamchatka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.tours_branch.NavToursFragment
import com.google.android.material.imageview.ShapeableImageView

class MoreInfoFragment : Fragment() {

    private var selectedTour: Tours? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedTour = it.getParcelable("selectedTour")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more_info, container, false)

        // Установка данных в элементы интерфейса
        selectedTour?.let { tour ->
            view.findViewById<TextView>(R.id.tvHeaderItem).text = tour.name
            view.findViewById<TextView>(R.id.tvPrice).text = tour.price
            view.findViewById<TextView>(R.id.tvDuration).text = tour.duration
            view.findViewById<TextView>(R.id.tvDate).text = tour.date
            view.findViewById<TextView>(R.id.tvWhatTakeDesc).text = tour.things_to_bag

            view.findViewById<ShapeableImageView>(R.id.ivMoreInfo).apply {
                Glide.with(this).load(tour.imageUrl).into(this)
            }
        }
        return view
    }

    companion object {
        fun newInstance(tour: Tours): MoreInfoFragment {
            val fragment = MoreInfoFragment()
            val args = Bundle().apply {
                putParcelable("selectedTour", tour)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
