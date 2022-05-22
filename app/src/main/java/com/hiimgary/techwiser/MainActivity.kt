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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.ui.main.MainStateEvent
import com.hiimgary.techwiser.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private var currentTechy: Techy? = null

    private lateinit var fbAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        fbAnalytics = Firebase.analytics
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
            currentTechy?.let { t ->
                logAddedToFavorite(t.quote)
                mainViewModel.markAsFavorite(t)
            }
            mainViewModel.setStateEvent(MainStateEvent.GetTechyQuote)
            loadImage()
        }

        val favoritesButton = findViewById<Button>(R.id.favorites)
        favoritesButton.setOnClickListener {
            val intent = Intent(baseContext, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    fun logAddedToFavorite(quote: String) {
        fbAnalytics.logEvent("add_to_favorite") {
            param("quote", quote) }
    }

    fun loadImage() {
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load("https://picsum.photos/400/600?blur=4?=" + System.currentTimeMillis()).into(imgView)
    }
}