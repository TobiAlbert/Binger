package ng.max.binger.activities.favorites

import ng.max.binger.data.TvShowDetail
import ng.max.binger.mvp.Mvp

interface FavouritesContract  {

    interface View : Mvp.View {
        fun showLoading()
        fun hideLoading()
        fun showShows(tvShows: List<TvShowDetail>)
        fun showError(msg: String)
    }

    interface Presenter: Mvp.Presenter<View> {
        fun getShows()
    }
}