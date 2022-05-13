package binar.ganda.challengechapter6.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseDataFilmItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
) : Parcelable