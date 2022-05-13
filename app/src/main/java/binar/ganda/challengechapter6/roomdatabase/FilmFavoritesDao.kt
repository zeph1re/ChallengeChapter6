package binar.ganda.challengechapter6.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmFavoritesDao {

    @Query ("SELECT * FROM FilmFavorites")
    fun getAllFilmFavorites() : LiveData<List<FilmFavorites>>

    @Insert
    fun insertFilmFavorites(filmFavorites: FilmFavorites) : Long

}