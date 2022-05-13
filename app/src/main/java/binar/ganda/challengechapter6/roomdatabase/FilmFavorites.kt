package binar.ganda.challengechapter6.roomdatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FilmFavorites(
    @PrimaryKey() val id :Int?,
    val date: String,
    val description: String,
    val director: String,
    val image: String,
    val name: String
) : Parcelable