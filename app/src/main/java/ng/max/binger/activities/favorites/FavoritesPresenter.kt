package ng.max.binger.activities.favorites

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.TvShowRepository
import ng.max.binger.mvp.BasePresenter

class FavoritesPresenter(private val repository: TvShowRepository.FavoriteRepository):
        BasePresenter<FavouritesContract.View>() , FavouritesContract.Presenter {

    override fun getShows() {
        view?.showLoading()
        repository.getFavoriteTvShows()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    view?.hideLoading()
                    view?.showShows(it)

                }) {
                    error: Throwable ->
                    view?.hideLoading()
                    view?.showError(error.message.toString())
                }
    }

}