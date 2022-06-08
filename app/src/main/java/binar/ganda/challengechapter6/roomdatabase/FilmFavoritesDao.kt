package binar.ganda.challengechapter6.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmFavoritesDao {

    @Query ("SELECT * FROM FilmFavorites")
    fun getAllFilmFavorites() : LiveData<List<FilmFavorites>>

    @Insert
    suspend fun insertFilmFavorites(filmFavorites: FilmFavorites) : Long

    @Delete
    suspend fun deleteFilmFavorites(filmFavorites: FilmFavorites) : Int

}