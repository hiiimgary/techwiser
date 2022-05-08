package com.hiimgary.techwiser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.ui.main.MainStateEvent
import com.hiimgary.techwiser.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var currentTechy: Techy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel.setStateEvent(MainStateEvent.GetTechyQuote)

        mainViewModel.state.observe(this, Observer { techy ->
            run {
                val text = findViewById<TextView>(R.id.textView)
                text.text = techy.quote
                currentTechy = techy
            }
        })
        loadImage()


        val refreshButton = findViewById<ImageButton>(R.id.refresh)
        refreshButton.setOnClickListener {
            mainViewModel.setStateEvent(MainStateEvent.GetTechyQuote)
            loadImage()
        }

        val favoriteButton = findViewById<ImageButton>(R.id.favorite)
        favoriteButton.setOnClickListener {
            currentTechy?.let { t -> mainViewModel.markAsFavorite(t) }
            mainViewModel.setStateEvent(MainStateEvent.GetTechyQuote)
            loadImage()
        }

        val favoritesButton = findViewById<Button>(R.id.favorites)
        favoritesButton.setOnClickListener {
            val intent = Intent(baseContext, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadImage() {
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load("https://picsum.photos/400/600?blur=4?=" + System.currentTimeMillis()).into(imgView)
    }
}