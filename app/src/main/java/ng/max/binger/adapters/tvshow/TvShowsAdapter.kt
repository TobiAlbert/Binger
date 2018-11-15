package ng.max.binger.adapters.tvshow

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_tv_show.view.*
import ng.max.binger.R
import ng.max.binger.activities.details.DetailsActivity
import ng.max.binger.data.FavoriteRepository
import ng.max.binger.data.TvShow
import ng.max.binger.data.TvShowRepository
import ng.max.binger.utils.DisplayUtils

class TvShowsAdapter: RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>(),
        TvShowAdapterContract.View {

    var tvShows = mutableListOf<TvShow>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context
    private lateinit var mFavRepository: TvShowRepository.FavoriteRepository
    private lateinit var mPresenter: TvShowAdapterContract.Presenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        mContext = parent.context
        mFavRepository = FavoriteRepository(mContext)
        mPresenter = TvShowAdapterPresenter(mFavRepository)
        mPresenter.attachView(this)

        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount() = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) = holder.bind(tvShows, position)

    override fun showError(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
                .show()
    }

    inner class TvShowViewHolder(view: View): RecyclerView.ViewHolder(view) {

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

            itemView.likeButton.setOnClickListener {
                onLikeButtonClicked(itemView.likeButton, tvShow, position)
            }
            
            itemView.videoTitle.setOnClickListener { onTvShowSelected(tvShow) }
            itemView.videoDescription.setOnClickListener { onTvShowSelected(tvShow) }
            itemView.videoPoster.setOnClickListener { onTvShowSelected(tvShow) }
        }

        private fun onLikeButtonClicked(view: ImageView, tvShow: TvShow, position: Int) {

            tvShow.isLiked = !tvShow.isLiked

            if (tvShow.isLiked) {
                view.setImageResource(R.drawable.ic_favorite)
                mPresenter.insertTvShowToDb(tvShow)
            } else {
                view.setImageResource(R.drawable.ic_favorite_holo)
                mPresenter.deleteTvShowFromDb(tvShow.id, position)
            }
        }

        private fun onTvShowSelected(tvShow: TvShow) {
            val intent = Intent(mContext, DetailsActivity::class.java).apply {
                putExtra("tvId",  tvShow.id)
            }
            startActivity(mContext, intent, null)
        }
    }
}