package ng.max.binger.adapters.tvshow

import ng.max.binger.data.TvShow
import ng.max.binger.mvp.Mvp

class TvShowAdapterContract {

    interface View: Mvp.View {
        fun showError(msg: String)
    }

    interface Presenter: Mvp.Presenter<View> {
        fun insertTvShowToDb(tvShow: TvShow)
        fun deleteTvShowFromDb(id: Int,  position: Int)
    }
}