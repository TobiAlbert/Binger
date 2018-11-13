package ng.max.binger.fragments.airing

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.TvShowRepository
import ng.max.binger.fragments.FragmentMvpContract
import ng.max.binger.mvp.BasePresenter

class AiringTodayPresenter(private val repository: TvShowRepository):
        BasePresenter<FragmentMvpContract.View>(),
        FragmentMvpContract.Presenter {

    override fun getShows() {
        view?.showLoading()
        repository.getAiringTodayShows()
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