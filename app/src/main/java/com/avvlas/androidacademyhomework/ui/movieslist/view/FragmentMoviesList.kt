package com.avvlas.androidacademyhomework.ui.movieslist.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avvlas.androidacademyhomework.R
import com.avvlas.androidacademyhomework.di.MovieRepositoryProvider
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.ui.movieslist.viewmodel.MoviesListViewModel
import com.avvlas.androidacademyhomework.ui.movieslist.viewmodel.MoviesListViewModelFactory
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import com.google.android.material.progressindicator.CircularProgressIndicator

class FragmentMoviesList : Fragment() {

    var progressIndicator: CircularProgressIndicator? = null

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
            (requireActivity() as MovieRepositoryProvider)
                .provideMovieRepository()
        )
    }

    private var listener: OnMovieSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnMovieSelectedListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressIndicator = view.findViewById(R.id.progress_bar_movies_list)

        view.findViewById<RecyclerView>(R.id.rv_movies_list).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MoviesListAdapter { movie ->
                listener?.onSelected(movie)
            }
            this.adapter = adapter
            loadData(adapter)
        }
    }

    private fun loadData(adapter: MoviesListAdapter) {
        viewModel.state.observe(this.viewLifecycleOwner) { state ->
            when(state){
                ViewState.Error -> moviesLoadError()
                ViewState.Loading -> showProgressIndicator() // TODO: why is it not visible??
                is ViewState.Success -> moviesLoadSuccess(adapter, state.data)
            }
        }
    }

    private fun showProgressIndicator() {
        progressIndicator?.isVisible = true
    }

    private fun hideProgressIndicator() {
        progressIndicator?.isVisible = false

    }

    private fun moviesLoadError() {
        hideProgressIndicator()
        Toast.makeText(
            requireContext(),
            "Couldn't load movies! Please check internet connection and try again",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun moviesLoadSuccess(adapter: MoviesListAdapter, movies : List<Movie>){
        hideProgressIndicator()
        adapter.submitList(movies)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface OnMovieSelectedListener {
        fun onSelected(movie: Movie)
    }

    companion object {
        fun create() = FragmentMoviesList()
    }
}