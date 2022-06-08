package binar.ganda.challengechapter6.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import binar.ganda.challengechapter6.view.adapter.FilmFavoritesAdapter
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity() {

    private var filmFavDB: FilmFavoritesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        filmFavDB = FilmFavoritesDatabase.getInstance(this)

        getDataFavFilm()
    }

    private fun getDataFavFilm() {
        rv_favorites_film.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        GlobalScope.launch {
            val listFavFilm = filmFavDB?.filmFavDao()?.getAllFilmFavorites()
            runOnUiThread {
                listFavFilm?.observe(this@Favorites) {
                    //set adapter
                    rv_favorites_film.adapter = FilmFavoritesAdapter(it)
                }
            }

        }
    }

}

