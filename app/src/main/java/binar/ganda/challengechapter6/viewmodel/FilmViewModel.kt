package binar.ganda.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import binar.ganda.challengechapter6.network.FilmRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {
    var liveDataFilm : MutableLiveData<List<ResponseDataFilmItem>?> = MutableLiveData()

    fun getLivedataFilm() : MutableLiveData<List<ResponseDataFilmItem>?> {
        return liveDataFilm
    }

    fun callApiFilm() {
        with(FilmRetrofit) {
            INSTANCE.getAllFilm()
                .enqueue(object : Callback<List<ResponseDataFilmItem>> {
                    override fun onResponse(
                        call: Call<List<ResponseDataFilmItem>>,
                        response: Response<List<ResponseDataFilmItem>>
                    ) {
                        if (response.isSuccessful){
                            liveDataFilm.postValue(response.body())
                        } else{
                            liveDataFilm.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                        liveDataFilm.postValue(null)
                    }

                })
        }
    }
}