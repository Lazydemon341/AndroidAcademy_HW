package com.avvlas.androidacademyhomework.moviedetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.avvlas.androidacademyhomework.R

class FragmentMovieDetails : Fragment() {

    var listener : OnBackClickListener? = null

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
            .setOnClickListener{
                listener?.onBackClick()
            }
    }

    interface OnBackClickListener {
        fun onBackClick()
    }

    companion object {
        fun create() = FragmentMovieDetails()
    }
}