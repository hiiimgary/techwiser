package com.hiimgary.techwiser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiimgary.techwiser.ui.favorites.FavoritesStateEvent
import com.hiimgary.techwiser.ui.favorites.FavoritesViewModel
import com.hiimgary.techwiser.ui.favorites.TechyListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesActivity: AppCompatActivity() {
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)

        favoritesViewModel.setStateEvent(FavoritesStateEvent.GetFavorites)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        favoritesViewModel.state.observe(this, Observer {
            techies -> recyclerView.adapter = TechyListAdapter(this, techies)
        })

        val homeButton = findViewById<Button>(R.id.home)
        homeButton.setOnClickListener {
            finish()
        }
    }
}