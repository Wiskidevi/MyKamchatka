package com.example.mykamchatka.tours_branch

import android.content.Intent
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mykamchatka.DataModel
import com.example.mykamchatka.data_classes.Tours
import com.example.mykamchatka.databinding.FragmentAddTourBinding
import com.example.mykamchatka.supabase.SupabaseHelper
import kotlinx.coroutines.launch


class AddTourFragment : Fragment() {

    private var _binding: FragmentAddTourBinding? = null
    private val binding get() = _binding!!


    private val dataModel: DataModel by viewModels()
    private lateinit var getContentLauncher: ActivityResultLauncher<String>

    companion object {
        fun newInstance() = AddTourFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getContentLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                val currentUris = dataModel.imageUris.value?.toMutableList() ?: mutableListOf()
                currentUris.add(uri)
                dataModel.imageUris.value = currentUris
            }
        }

        binding.ibtnAddImage.setOnClickListener{
            getContentLauncher.launch("image/*")
        }

        binding.btnPublishTour.setOnClickListener{
            saveTourToDatabase()
        }
    }

    private fun uriToByteArray(uri: Uri?): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri!!)
        return inputStream?.readBytes() ?: throw IllegalArgumentException("Failed to read URI: $uri")
    }

    private suspend fun uploadImagesToSupabase(): List<String> {
        val uris = dataModel.imageUris.value ?: return emptyList()
        val uploadedImageUrls = mutableListOf<String>()

        val db = SupabaseHelper()
        for ((index, uri) in uris.withIndex()) {
            try {
                val fileName = "tour_image_${System.currentTimeMillis()}_$index.jpg"
                val byteArray = uriToByteArray(uri) // Преобразуем Uri в ByteArray
                val imageUrl = db.uploadImage("ToursImagesBucket", fileName, byteArray) // Загрузка изображения
                uploadedImageUrls.add(imageUrl) // Добавляем URL в список
            } catch (e: Exception) {
                Log.e("Supabase", "Failed to upload image: $uri", e)
            }
        }
        return uploadedImageUrls
    }


    private fun saveTourToDatabase() {
        val tourName = binding.tfTourName.text.toString()
        val tourDate = binding.tfTourDate.text.toString()
        val tourDuration = binding.tfTourDuration.text.toString()
        val tourPrice = binding.tfTourPrice.text.toString()
        val thingsToBag = ""

        if (tourName.isBlank() || tourDate.isBlank()) {
            Log.e("SaveTour", "Name or Date cannot be empty")
            return
        }

        lifecycleScope.launch {
            try {
                // Загружаем изображения
                val uploadedImageUrls = uploadImagesToSupabase()

                // Создаем объект Tours
                val newTour = Tours(
                    name = tourName,
                    date = tourDate,
                    duration = tourDuration,
                    price = tourPrice,
                    imageUrl = uploadedImageUrls,
                    things_to_bag = thingsToBag
                )

                // Сохраняем данные в таблицу
                SupabaseHelper().setData("ToursTable", listOf(newTour))
                Log.i("SaveTour", "Tour saved successfully!")
            } catch (e: Exception) {
                Log.e("SaveTour", "Failed to save tour", e)
            }
        }
    }









}