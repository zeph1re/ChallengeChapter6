package binar.ganda.challengechapter6.network

import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import retrofit2.Call
import retrofit2.http.GET

interface FilmService {

    @GET("film")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>




}