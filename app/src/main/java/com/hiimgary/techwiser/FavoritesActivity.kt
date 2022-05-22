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
}