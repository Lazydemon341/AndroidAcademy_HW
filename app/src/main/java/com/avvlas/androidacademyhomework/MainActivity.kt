package com.avvlas.androidacademyhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.avvlas.androidacademyhomework.moviedetails.FragmentMovieDetails
import com.avvlas.androidacademyhomework.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity(),
    FragmentMovieDetails.OnBackClickListener,
    FragmentMoviesList.OnMovieSelectedListener {

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

    override fun onSelected() {
        toMovieDetails()
    }

    private fun toMovieDetails() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, FragmentMovieDetails.create())
            .addToBackStack("Show Movie Details")
            .commit()
    }

    override fun onBackClick() {
        supportFragmentManager.popBackStack()
    }
}