package binar.ganda.challengechapter6.view.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import binar.ganda.challengechapter6.MainActivity
import binar.ganda.challengechapter6.R
import binar.ganda.challengechapter6.roomdatabase.FilmFavorites
import binar.ganda.challengechapter6.roomdatabase.FilmFavoritesDatabase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.favorites_item_list.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FilmFavoritesAdapter(private var listFilmFavorites: List<FilmFavorites>?): RecyclerView.Adapter<FilmFavoritesAdapter.ViewHolder>() {

    private var filmFavDB: FilmFavoritesDatabase? = null

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

        holder.itemView.favorites_check_box.setOnClickListener {
            var isFavorites = holder.itemView.favorites_check_box.isChecked
            if (isFavorites){
                filmFavDB = FilmFavoritesDatabase.getInstance(it.context)

                AlertDialog.Builder(it.context)
                    .setTitle("Hapus Data")
                    .setMessage("Yakin Hapus Data")
                    .setPositiveButton("Ya") { dialogInterface: DialogInterface, i : Int ->
                        GlobalScope.async {
                            val result = filmFavDB?.filmFavDao()?.deleteFilmFavorites(
                                listFilmFavorites!![position])

                            (holder.itemView.context as MainActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(it.context, "Data ${listFilmFavorites!![position].name} Terhapus", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(it.context, "Data ${listFilmFavorites!![position].name} Gagal terhapus", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Tidak") { dialogInterface: DialogInterface, i : Int ->
                        dialogInterface.dismiss()
                        isFavorites = holder.itemView.favorites_check_box.isChecked
                    }
                    .show()

            }
        }

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