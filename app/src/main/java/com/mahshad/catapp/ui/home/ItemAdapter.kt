package com.mahshad.catapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Breed
import com.mahshad.catapp.R

class ItemAdapter(private val breeds: List<Breed>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemViewHolder>() {

    companion object {
        private val TAG = "TAGItemAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(itemView = itemView, listener = listener, breeds = breeds)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = breeds[position]
        holder.titleTextView.text = currentItem.name
        holder.descriptionTextView.text = currentItem.description

        Glide.with(holder.itemView.context)
            .load(currentItem.image?.url)
            .into(holder.itemImageView)
    }

    override fun getItemCount(): Int {
        return breeds.size
    }
}

class ItemViewHolder(
    breeds: List<Breed>,
    itemView: View,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position = position, id = breeds[position].id)
            }
        }
    }

    val titleTextView: TextView = itemView.findViewById(R.id.itemTitle)
    val descriptionTextView: TextView = itemView.findViewById(R.id.itemDescription)
    val itemImageView: ImageView = itemView.findViewById(R.id.itemImage)
}

interface OnItemClickListener {
    fun onItemClick(position: Int, id: String?)
}