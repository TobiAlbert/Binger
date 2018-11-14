package ng.max.binger.adapters.favorite

import ng.max.binger.mvp.Mvp

interface FavoriteAdapterContract {

    interface View: Mvp.View {
        fun showError(msg: String)
        fun removeItemFromView(position: Int)
    }

    interface Presenter: Mvp.Presenter<View> {
        fun deleteTvShowFromDb(id: Int,  position: Int)
    }
}