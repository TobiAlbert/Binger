package ng.max.binger.fragments.popular

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.remote.CloudTvShowRepository
import ng.max.binger.fragments.FragmentMvpContract
import ng.max.binger.mvp.BasePresenter

class PopularShowPresenter(private val repository: CloudTvShowRepository):
        BasePresenter<FragmentMvpContract.View>(),
        FragmentMvpContract.Presenter {

    override fun getShows() {
        view?.showLoading()
        repository.getPopularTvShows()
                .subscribeOn(Schedulers.io())
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
