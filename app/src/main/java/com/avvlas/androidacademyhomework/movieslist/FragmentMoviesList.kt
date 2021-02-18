package com.avvlas.androidacademyhomework.movieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avvlas.androidacademyhomework.R
import com.avvlas.androidacademyhomework.data.MovieRepositoryProvider
import com.avvlas.androidacademyhomework.model.Movie

class FragmentMoviesList : Fragment() {

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

        view.findViewById<RecyclerView>(R.id.rv_movies_list).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MoviesListAdapter { movie ->
                listener?.onSelected(movie)
            }
            this.adapter = adapter
            loadDataToAdapter(adapter)
        }
    }

    private fun loadDataToAdapter(adapter: MoviesListAdapter) {
        viewModel.moviesData.observe(this.viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }
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