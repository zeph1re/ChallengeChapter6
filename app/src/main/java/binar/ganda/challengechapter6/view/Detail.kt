package binar.ganda.challengechapter6.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import binar.ganda.challengechapter6.roomdatabase.FilmFavorites
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Detail : AppCompatActivity() {

    private var filmFavDB : FilmFavoritesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getAllDetail()
        addFilmToFavorites()


    }

    private fun getAllDetail() {

        val b = intent.extras

        val details = b?.getParcelable<ResponseDataFilmItem>("DATA_FILM")
        val judul = details?.name.toString()
        val releaseDate = details?.date.toString()
        val sutradara = details?.director.toString()
        val image = details?.image.toString()
        val sinopsis = details?.description.toString()


        tv_detail_judul.text = judul
        tv_detail_sutradara.text = sutradara
        tv_detail_tanggal_rilis.text = releaseDate
        tv_detail_sinopsis.text = sinopsis

        Glide.with(this).load(image).into(detail_image)
    }

    private fun addFilmToFavorites() {
        favorites_btn.setOnClickListener {

            val details =intent.getParcelableExtra<ResponseDataFilmItem>("DATA_FILM")
            GlobalScope.async {
                val judul = details?.name.toString()
                val releaseDate = details?.date.toString()
                val sutradara = details?.director.toString()
                val image = details?.image.toString()
                val sinopsis = details?.description.toString()
                val favFilm = FilmFavorites(null, releaseDate, sinopsis, sutradara, image, judul)
                val result = filmFavDB?.filmFavDao()?.insertFilmFavorites(favFilm)
                            runOnUiThread {
                                if (result != 0.toLong()) {
                                    Toast.makeText(this@Detail, "Item added to Favorites", Toast.LENGTH_LONG)
                                        .show()
                                } else {
                                    Toast.makeText(this@Detail, "Gagal", Toast.LENGTH_LONG).show()
                                }
                            }
                }

            startActivity(Intent(this, Favorites::class.java))

        }
    }
}