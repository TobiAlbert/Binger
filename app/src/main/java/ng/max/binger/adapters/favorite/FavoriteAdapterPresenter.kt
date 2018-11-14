package ng.max.binger.adapters.favorite

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.TvShowRepository

class FavoriteAdapterPresenter(private val localRepo: TvShowRepository.FavoriteRepository):
        FavoriteAdapterContract.Presenter{

    private lateinit var view: FavoriteAdapterContract.View

    override fun attachView(view: FavoriteAdapterContract.View) {
        this.view = view
    }

    override fun detachView() {

    }

    override fun deleteTvShowFromDb(id: Int, position: Int) {
        view.removeItemFromView(position)
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