package com.avvlas.androidacademyhomework.ui.movieslist.view

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avvlas.androidacademyhomework.R
import com.avvlas.androidacademyhomework.model.Movie
import com.bumptech.glide.Glide

class MoviesListAdapter(private val onCardClickListener: (Item: Movie) -> Unit) :
    ListAdapter<Movie, MoviesListAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onCardClickListener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImage: ImageView = itemView.findViewById(R.id.movie_card_image)
        private val likeImage: ImageView = itemView.findViewById(R.id.movie_card_like)
        private val pgText: TextView = itemView.findViewById(R.id.movie_card_pg_text)
        private val genreText: TextView = itemView.findViewById(R.id.movie_card_genre_text)
        private val stars: List<ImageView> = listOf(
            itemView.findViewById(R.id.movie_card_star_1),
            itemView.findViewById(R.id.movie_card_star_2),
            itemView.findViewById(R.id.movie_card_star_3),
            itemView.findViewById(R.id.movie_card_star_4),
            itemView.findViewById(R.id.movie_card_star_5)
        )
        private val reviewsText: TextView =
            itemView.findViewById(R.id.movie_card_reviews_number_text)
        private val titleText: TextView = itemView.findViewById(R.id.movie_card_title_text)
        private val durationText: TextView = itemView.findViewById(R.id.movie_card_duration)

        fun bind(movie: Movie, onCardClick: (Item: Movie) -> Unit) {
            // TODO: change size
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .error(R.drawable.mask)
                .placeholder(R.drawable.mask)
                .centerCrop()
                .into(movieImage)

            pgText.text = (movie.pgAge.toString() + "+")
            genreText.text = movie.genres.joinToString { it.name }
            reviewsText.text = (movie.reviewCount.toString() + "reviews")
            titleText.text = movie.title

            // set like tint
            val likeColor = if (movie.isLiked) {
                R.color.red
            } else {
                R.color.white
            }
            ImageViewCompat.setImageTintList(
                likeImage, ColorStateList.valueOf(
                    ContextCompat.getColor(likeImage.context, likeColor)
                )
            )

            //set stars tint
            stars.forEachIndexed { index, imageView ->
                val colorId = if (movie.rating > index) R.color.red else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }

            itemView.setOnClickListener { onCardClick(movie) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.equals(newItem)
        }

    }
}