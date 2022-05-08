package com.hiimgary.techwiser.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hiimgary.techwiser.R
import com.hiimgary.techwiser.model.Techy

class TechyListAdapter(
    private val context: Context,
    private val dataset: List<Techy>
): RecyclerView.Adapter<TechyListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.techy_list_item_text)
        val card: CardView = view.findViewById(R.id.techy_list_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.text.text = dataset[position].quote
    }

    override fun getItemCount() = dataset.size
}