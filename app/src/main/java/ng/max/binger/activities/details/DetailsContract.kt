package ng.max.binger.activities.details

import ng.max.binger.data.TvShowDetail
import ng.max.binger.mvp.Mvp

interface DetailsContract {

    interface View: Mvp.View {
        fun displayTvShow(tvShow: TvShowDetail)
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
    }

    interface Presenter: Mvp.Presenter<View> {
        fun getTvShowDetails(tvId: Int)
    }
}