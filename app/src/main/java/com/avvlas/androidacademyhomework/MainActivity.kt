package com.avvlas.androidacademyhomework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.data.remote.retrofit.RetrofitStorage
import com.avvlas.androidacademyhomework.di.MovieRepositoryProvider
import com.avvlas.androidacademyhomework.di.NetworkModule
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.repository.MovieRepositoryImpl
import com.avvlas.androidacademyhomework.ui.moviedetails.view.FragmentMovieDetails
import com.avvlas.androidacademyhomework.ui.movieslist.view.FragmentMoviesList

class MainActivity : AppCompatActivity(),
    FragmentMovieDetails.OnBackClickListener,
    FragmentMoviesList.OnMovieSelectedListener,
    MovieRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitStorage(networkModule.api)
    private val repository = MovieRepositoryImpl.getInstance(remoteDataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            toMoviesList()

        supportFragmentManager.addOnBackStackChangedListener {
            var str: String = "Backstack:\n"
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
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
        toMovieDetails(movie.id)
    }

    private fun toMovieDetails(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, FragmentMovieDetails.create(movieId))
            .addToBackStack("Show Movie Details")
            .commit()
    }

    override fun onBackClick() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository {
        return repository
    }
}