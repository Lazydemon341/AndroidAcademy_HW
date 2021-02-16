package com.avvlas.androidacademyhomework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.homework.data.JsonMovieRepository
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.di.MovieRepositoryProvider
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.moviedetails.FragmentMovieDetails
import com.avvlas.androidacademyhomework.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity(),
    FragmentMovieDetails.OnBackClickListener,
    FragmentMoviesList.OnMovieSelectedListener,
    MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            toMoviesList()


        supportFragmentManager.addOnBackStackChangedListener {
            var str: String = "Backstack:\n"
            for (i in 0..(supportFragmentManager.backStackEntryCount - 1)) {
                str += supportFragmentManager.getBackStackEntryAt(i).toString() + "\n"
            }
            Log.d("backStack", str)
        }
    }

    private fun toMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, FragmentMoviesList.create())
            .addToBackStack("Show Movies List")
            .commit()
    }

    override fun onSelected(movie: Movie) {
        toMovieDetails(movie)
    }

    private fun toMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, FragmentMovieDetails.create(movie))
            .addToBackStack("Show Movie Details")
            .commit()
    }

    override fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository
}