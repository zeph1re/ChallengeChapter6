package binar.ganda.challengechapter6.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FilmFavorites::class], version = 1)
abstract class FilmFavoritesDatabase : RoomDatabase() {

    abstract fun filmFavDao() : FilmFavoritesDao

    companion object {
        private var INSTANCE : FilmFavoritesDatabase? = null
        fun getInstance (context: Context): FilmFavoritesDatabase? {
            if (INSTANCE == null) {
                synchronized(FilmFavoritesDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FilmFavoritesDatabase::class.java,"FilmFavorites.db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}