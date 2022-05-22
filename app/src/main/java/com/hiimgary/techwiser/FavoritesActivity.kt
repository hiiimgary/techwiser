package com.hiimgary.techwiser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.hiimgary.techwiser.ui.favorites.FavoritesStateEvent
import com.hiimgary.techwiser.ui.favorites.FavoritesViewModel
import com.hiimgary.techwiser.ui.favorites.TechyListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesActivity: AppCompatActivity() {
    private lateinit var fbAnalytics: FirebaseAnalytics
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        fbAnalytics = Firebase.analytics
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)

        favoritesViewModel.setStateEvent(FavoritesStateEvent.GetFavorites)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        favoritesViewModel.state.observe(this, Observer {
            techies ->
                var adapter = TechyListAdapter(this, techies)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : TechyListAdapter.onItemClickListener{
                    override fun onEditClicked(position: Int) {
                        val intent = Intent(baseContext, NewActivity::class.java)
                        intent.putExtra("QUOTE", techies[position].quote)
                        startActivity(intent)
                    }

                    override fun onDeleteClicked(position: Int) {
                        logDeleteFavorite(techies[position].quote)
                        favoritesViewModel.deleteFavorite(techies[position])
                    }
                })
        })

        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            finish()
        }

        val addButton = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.add)
        addButton.setOnClickListener {
            val intent = Intent(baseContext, NewActivity::class.java)
            intent.putExtra("QUOTE", "")
            startActivity(intent)
        }
    }

    public override fun onResume() {
        super.onResume()
        favoritesViewModel.setStateEvent(FavoritesStateEvent.GetFavorites)
    }

    fun logDeleteFavorite(quote: String) {
        fbAnalytics.logEvent("delete_favorite") {
            param("quote", quote) }
    }
}