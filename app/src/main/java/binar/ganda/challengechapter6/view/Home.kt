package binar.ganda.challengechapter6.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.view.adapter.FilmAdapter
import binar.ganda.challengechapter6.manager.UserManager
import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import binar.ganda.challengechapter6.roomdatabase.FilmFavorites
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import binar.ganda.challengechapter6.viewmodel.FilmViewModel
import binar.ganda.challengechapter6.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class Home : AppCompatActivity() {

    private lateinit var adapterFilm : FilmAdapter
    lateinit var userManager: UserManager
    lateinit var usernameLogin: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        initrecycler()
        getDataFilm()
        toProfile()
        getName()
        toFavorites()
    }



    fun toFavorites() {
        favorites.setOnClickListener {
            startActivity(Intent(this,Favorites::class.java))
        }
    }

    private fun initrecycler() {
        rv_films.layoutManager = LinearLayoutManager(this)
        adapterFilm = FilmAdapter(){
            val clickedFilm = bundleOf("DATA_FILM" to it)
            val pindah = Intent(this, Detail::class.java)
            pindah.putExtras(clickedFilm)
            startActivity(pindah)
        }
        rv_films.adapter = adapterFilm
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFilm() {
        val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)

        viewModelFilm.getLivedataFilm().observe(this) {
            if (it != null) {
                adapterFilm.setDataFilm(it)
                adapterFilm.notifyDataSetChanged()
                Log.d("test", it.toString())
            }
        }
        viewModelFilm.callApiFilm()
    }

    private fun toProfile() {
        image_profile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    fun getName() {

        userManager = UserManager(this)

        userManager.userUsername.asLiveData().observe(this) {
            usernameLogin = it.toString()
        }

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getLivedataUser().observe(this, Observer{
                if (usernameLogin != null) {
                    val name = usernameLogin
                    tv_username.text = "Hello, $name"
                } else {
                    tv_username.text = "Hello, there!"
                }

        })
        userViewModel.callApiUser()
    }

}