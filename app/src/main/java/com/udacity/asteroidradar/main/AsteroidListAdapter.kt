package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ListItemBinding

class AsteroidListAdapter (val onClickListener: AsteroidClickListener) : ListAdapter<Asteroid, AsteroidListAdapter.AsteroidListViewHolder>(DiffCallback){

    class AsteroidListViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid){
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return AsteroidListViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>(){
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidListViewHolder {
        return AsteroidListViewHolder.from(parent)//AsteroidListViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidListViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }
}

class AsteroidClickListener(val clickListener: (asteroid: Asteroid) -> Unit){
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}