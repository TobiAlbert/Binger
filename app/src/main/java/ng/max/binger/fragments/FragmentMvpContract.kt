package ng.max.binger.fragments

import ng.max.binger.data.TvShow
import ng.max.binger.mvp.Mvp

interface FragmentMvpContract {

    interface View: Mvp.View {
        fun showLoading()
        fun hideLoading()
        fun showError(msg: String)
        fun showShows(tvShows: List<TvShow>)
    }

    interface Presenter: Mvp.Presenter<View>{
        fun getShows()
    }
}