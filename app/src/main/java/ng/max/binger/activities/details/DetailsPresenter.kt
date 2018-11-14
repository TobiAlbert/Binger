package ng.max.binger.activities.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.TvShowDetail
import ng.max.binger.data.TvShowRepository
import ng.max.binger.mvp.BasePresenter

class DetailsPresenter(private val repository: TvShowRepository):
        BasePresenter<DetailsContract.View>(),
        DetailsContract.Presenter {

    override fun getTvShowDetails(tvId: Int) {
        view?.showLoading()
        repository.getTvShowDetails(tvId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tvShow: TvShowDetail ->
                    view?.hideLoading()
                    view?.displayTvShow(tvShow)
                }) {
                    error: Throwable ->
                    view?.hideLoading()
                    view?.showError(error.message.toString())
                }
    }

}