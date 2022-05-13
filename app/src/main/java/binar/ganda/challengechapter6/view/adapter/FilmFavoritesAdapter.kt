package binar.ganda.challengechapter6.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class FilmFavoritesAdapter(): RecyclerView.Adapter<FilmFavoritesAdapter.ViewHolder>() {


    private var listFilmFavorites : List<ResponseDataFilmItem>? = null


    fun setDataFilmFavorites(film : List<ResponseDataFilmItem>){
        this.listFilmFavorites = film
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.favorites_item_list, parent, false)
        return   ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tv_judul.text = listFilmFavorites!![position].name
        holder.itemView.tv_tanggal_rilis.text = listFilmFavorites!![position].date
        holder.itemView.tv_sutradara.text = listFilmFavorites!![position].director

        Glide.with(holder.itemView.context).load(listFilmFavorites!![position].image).into(holder.itemView.image_film)

//        holder.itemView.cardFilm.setOnClickListener {
//            onClick(listFilmFavorites!![position])
//        }

    }


    override fun getItemCount(): Int {
        return if (listFilmFavorites == null) {
            0
        } else {
            listFilmFavorites!!.size
        }
    }
}