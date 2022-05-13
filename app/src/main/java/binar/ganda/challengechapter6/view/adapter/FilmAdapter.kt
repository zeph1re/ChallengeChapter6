package binar.ganda.challengechapter6.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.model.ResponseDataFilmItem
import binar.ganda.challengechapter6.roomdatabase.FilmFavorites
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import binar.ganda.challengechapter6.view.Detail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async



class FilmAdapter(private val onClick: (ResponseDataFilmItem) -> Unit) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var dataFilm : List<ResponseDataFilmItem>? = null


    fun setDataFilm(film : List<ResponseDataFilmItem>){
        this.dataFilm = film
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val tampilanListFilm =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(tampilanListFilm)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_judul.text = dataFilm!![position].name
        holder.itemView.tv_tanggal_rilis.text = dataFilm!![position].date
        holder.itemView.tv_sutradara.text = dataFilm!![position].director

        Glide.with(holder.itemView.context).load(dataFilm!![position].image)
            .into(holder.itemView.image_film)

        holder.itemView.cardFilm.setOnClickListener {
            onClick(dataFilm!![position])
        }


    }


    override fun getItemCount(): Int {
        return if (dataFilm == null) {
            0
        } else {
            dataFilm!!.size
        }
    }


}