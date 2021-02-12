package com.avvlas.androidacademyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentMoviesList : Fragment() {

    var listener: OnMovieSelectedListener? = null

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

    override fun onStart() {
        super.onStart()

        view?.findViewById<View>(R.id.movie_list_item_card_layout)?.setOnClickListener {
            listener?.onSelected()
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface OnMovieSelectedListener {
        fun onSelected()
    }

    companion object {
        fun create() = FragmentMoviesList()
    }
}