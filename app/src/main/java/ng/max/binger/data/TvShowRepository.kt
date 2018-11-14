package ng.max.binger.data

import io.reactivex.Observable

interface TvShowRepository {

    fun getPopularTvShows(): Observable<List<TvShow>>
    fun getAiringTodayShows(): Observable<List<TvShow>>
    fun getTvShowDetails(tvId: Int): Observable<TvShowDetail>

    interface FavoriteRepository {
        fun getFavoriteTvShows(): Observable<List<TvShowDetail>>
    }
}