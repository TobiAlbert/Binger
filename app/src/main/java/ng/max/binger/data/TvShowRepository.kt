package ng.max.binger.data

import io.reactivex.Observable

interface TvShowRepository {

    fun getPopularTvShows(): Observable<List<TvShow>>
    fun getAiringTodayShows(): Observable<List<TvShow>>

    interface FavoriteRepository: TvShowRepository {
        fun getFavoriteTvShows(): Observable<List<TvShow>>
    }
}