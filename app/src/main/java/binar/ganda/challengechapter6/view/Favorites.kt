package binar.ganda.challengechapter6.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.view.adapter.FilmFavoritesAdapter
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import binar.ganda.challengechapter6.view.adapter.FilmAdapter
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity() {

    private var filmFavDB : FilmFavoritesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        filmFavDB = FilmFavoritesDatabase.getInstance(this)

        getDataFavFilm()
    }

    fun getDataFavFilm() {
        rv_favorites_film.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        GlobalScope.launch {
            val listFavFilm = filmFavDB?.filmFavDao()?.getAllFilmFavorites()
            runOnUiThread{
                listFavFilm.let {
                    //set adapter
                    rv_favorites_film.adapter = FilmFavoritesAdapter()


                }
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        getDataFavFilm()
    }
}