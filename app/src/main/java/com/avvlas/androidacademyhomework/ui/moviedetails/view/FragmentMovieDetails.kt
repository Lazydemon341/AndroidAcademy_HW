package com.avvlas.androidacademyhomework.ui.moviedetails.view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avvlas.androidacademyhomework.R
import com.avvlas.androidacademyhomework.di.MovieRepositoryProvider
import com.avvlas.androidacademyhomework.model.MovieDetails
import com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel.MovieDetailsViewModelFactory
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState

class FragmentMovieDetails : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(
            (requireActivity() as MovieRepositoryProvider)
                .provideMovieRepository()
        )
    }

    private var listener: OnBackClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBackClickListener)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.movie_details_back_text)
            .setOnClickListener {
                listener?.onBackClick()
            }

        val movieId = arguments?.getInt(PARAM_MOVIE_ID) as? Int ?: return
        viewModel.loadMovieDetails(movieId)
        viewModel.state.observe(this.viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success<MovieDetails> -> initUi(view, state.data)
                ViewState.Error -> showMovieLoadError()
                //ViewState.Loading -> TODO()
            }
        }
    }

    private fun initUi(view: View, movie: MovieDetails) {
        initMovieDetails(view, movie)
        initActorsRecyclerView(view, movie)
    }

    private fun initActorsRecyclerView(view: View, movie: MovieDetails) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_actors_list)
        val adapter = ActorsListAdapter()
        adapter.submitList(movie.actors)
        recyclerView.adapter = adapter
    }

    private fun initMovieDetails(view: View, movie: MovieDetails) {
        view.findViewById<ImageView>(R.id.movie_logo_image)
            .load(movie.detailImageUrl) {
                crossfade(true)
            }

        view.findViewById<TextView>(R.id.movie_details_pg_text)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view.findViewById<TextView>(R.id.movie_details_title_text)?.text = movie.title
        view.findViewById<TextView>(R.id.movie_details_genres_text)?.text =
            movie.genres.joinToString { it.name }
        view.findViewById<TextView>(R.id.movie_details_reviews_number_text)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view.findViewById<TextView>(R.id.storyline_text)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view.findViewById(R.id.movie_details_star_1),
            view.findViewById(R.id.movie_details_star_2),
            view.findViewById(R.id.movie_details_star_3),
            view.findViewById(R.id.movie_details_star_4),
            view.findViewById(R.id.movie_details_star_5)
        )
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.red else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    private fun showMovieLoadError() {

        Toast.makeText(
            requireContext(),
            "Couldn't load movie details! Please check internet connection and try again",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    interface OnBackClickListener {
        fun onBackClick()
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun create(movieId: Int) = FragmentMovieDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }
}