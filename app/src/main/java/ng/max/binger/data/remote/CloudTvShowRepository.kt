package ng.max.binger.data.remote

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ng.max.binger.data.PagedList
import ng.max.binger.data.TvShow
import ng.max.binger.data.TvShowDetail
import ng.max.binger.data.TvShowRepository

class CloudTvShowRepository(private val mService: TvShowService): TvShowRepository {

    //TODO: Pass TvShowService Dependency via DI

    override fun getPopularTvShows(): Observable<List<TvShow>> {
        return mService.getPopularTvShows()
                .flatMap {
                    pageList: PagedList<TvShow> ->
                    Observable.fromArray(pageList.results)
                }

    }

    override fun getAiringTodayShows(): Observable<List<TvShow>> {
        return mService.getAiringTodayShows()
                .flatMap {
                    pageList: PagedList<TvShow> ->
                    Observable.fromArray(pageList.results)
                }
    }

    override fun getTvShowDetails(tvId: Int): Observable<TvShowDetail> {
        return mService.getTvDetails(tvId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
