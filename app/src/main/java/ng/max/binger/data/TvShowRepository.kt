package ng.max.binger.data

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

interface TvShowRepository {

    fun getPopularTvShows(): Observable<List<TvShow>>
    fun getAiringTodayShows(): Observable<List<TvShow>>
    fun getTvShowDetails(tvId: Int): Observable<TvShowDetail>

    interface FavoriteRepository {
        fun getFavoriteTvShows(): Flowable<List<TvShow>>
        fun insertTvShow(favoriteShow: FavoriteShow): Maybe<Long>
        fun deleteTvShow(id: Int): Maybe<Int>
    }
}