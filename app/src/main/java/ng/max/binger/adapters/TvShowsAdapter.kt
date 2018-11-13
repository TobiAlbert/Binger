package ng.max.binger.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_tv_show.view.*
import ng.max.binger.R
import ng.max.binger.data.TvShow
import ng.max.binger.utils.DisplayUtils

class TvShowsAdapter: RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    var tvShows = emptyList<TvShow>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount() = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) = holder.bind(tvShows[position])

    inner class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(tvShow: TvShow) {
            Glide.with(mContext)
                    .load(DisplayUtils.getImageUrl(tvShow.posterPath))
                    .into(itemView.videoPoster)

            itemView.videoTitle.text = tvShow.name
            itemView.videoDescription.text = tvShow.summary
            itemView.videoRating.text = tvShow.rating.toString()
            itemView.productionYear.text = DisplayUtils.getYear(tvShow.firstAirDate)

            itemView.likeButton.setOnClickListener { onLikeButtonClicked(itemView.likeButton, tvShow) }
            itemView.setOnClickListener { onTvShowSelected(tvShow) }
            itemView.videoPoster.setOnClickListener { onTvShowSelected(tvShow) }
        }

        private fun onLikeButtonClicked(view: ImageView, tvShow: TvShow) {

            tvShow.isLiked = !tvShow.isLiked

            if (tvShow.isLiked) {
                view.setImageResource(R.drawable.ic_favorite)
            } else {
                view.setImageResource(R.drawable.ic_favorite_holo)
            }
        }

        private fun onTvShowSelected(tvShow: TvShow) {
            Toast.makeText(mContext, "${tvShow.name} Selected", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}