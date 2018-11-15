package ng.max.binger.adapters.favorite

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_tv_show.view.*
import ng.max.binger.R
import ng.max.binger.activities.details.DetailsActivity
import ng.max.binger.data.TvShow
import ng.max.binger.utils.DisplayUtils

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    var tvShows = mutableListOf<TvShow>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item_tv_show, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount() = tvShows.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) = holder.bind(tvShows, position)

    inner class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(tvShows: List<TvShow>, position: Int) {
            val tvShow = tvShows[position]
            Glide.with(mContext)
                    .load(DisplayUtils.getImageUrl(tvShow.posterPath))
                    .into(itemView.videoPoster)

            itemView.videoTitle.text = tvShow.name
            itemView.videoDescription.text = tvShow.summary
            itemView.videoRating.text = tvShow.rating.toString()
            itemView.productionYear.text = DisplayUtils.getYear(tvShow.firstAirDate)

            if (tvShow.isLiked) {
                itemView.likeButton.setImageResource(R.drawable.ic_favorite)
            } else {
                itemView.likeButton.setImageResource(R.drawable.ic_favorite_holo)
            }

            itemView.videoTitle.setOnClickListener { onTvShowSelected(tvShow) }
            itemView.videoDescription.setOnClickListener { onTvShowSelected(tvShow) }
            itemView.videoPoster.setOnClickListener { onTvShowSelected(tvShow) }
        }

        private fun onTvShowSelected(tvShow: TvShow) {
            val intent = Intent(mContext, DetailsActivity::class.java).apply {
                putExtra("tvId",  tvShow.id)
            }
            ContextCompat.startActivity(mContext, intent, null)
        }
    }

}
