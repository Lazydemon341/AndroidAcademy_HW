package com.avvlas.androidacademyhomework.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avvlas.androidacademyhomework.R
import com.avvlas.androidacademyhomework.model.Actor

class ActorsListAdapter : ListAdapter<Actor, ActorsListAdapter.ActorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.actor_name)
        val image: ImageView = itemView.findViewById(R.id.actor_image)

        fun bind(actor: Actor) {
            name.text = actor.name
            image.setImageResource(actor.imageRes)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem == newItem
        }
    }
}