package ng.max.binger.adapters.tvshow

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.FavoriteShow
import ng.max.binger.data.TvShow
import ng.max.binger.data.TvShowRepository

class TvShowAdapterPresenter(private val localRepo: TvShowRepository.FavoriteRepository):
        TvShowAdapterContract.Presenter {

    private lateinit var view: TvShowAdapterContract.View

    override fun attachView(view: TvShowAdapterContract.View) {
        this.view = view
    }

    override fun detachView() {
    }


    override fun insertTvShowToDb(tvShow: TvShow) {

        val favoriteShow = FavoriteShow().apply {
            id = tvShow.id
            tvShowId = tvShow.id
            name = tvShow.name
            rating = tvShow.rating
            summary = tvShow.summary
            voteCount = tvShow.voteCount
            posterPath = tvShow.posterPath
            backdropPath = tvShow.backdropPath
        }

        localRepo.insertTvShow(favoriteShow)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }) {
                    error: Throwable ->
                    view.showError(error.message.toString())
                }
    }

    override fun deleteTvShowFromDb(id: Int, position: Int) {
        localRepo.deleteTvShow(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({


                }) {
                    error: Throwable ->
                    view.showError(error.message.toString())
                }
    }
}