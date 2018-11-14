package ng.max.binger.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_genre.view.*
import ng.max.binger.R
import ng.max.binger.data.Genre

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    var tvGenres = emptyList<Genre>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, layoutResource: Int): GenreViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.list_item_genre, parent, false)
        return GenreViewHolder(itemView)
    }

    override fun getItemCount(): Int = tvGenres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) = holder.bind(tvGenres[position])


    inner class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(tvGenre: Genre) {
            itemView.genreName.text = tvGenre.name
        }
    }
}