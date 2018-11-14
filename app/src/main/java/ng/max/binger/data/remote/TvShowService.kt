package ng.max.binger.data.remote

import io.reactivex.Observable
import ng.max.binger.data.PagedList
import ng.max.binger.data.TvShow
import ng.max.binger.data.TvShowDetail
import ng.max.binger.utils.TMDB
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowService {

    @GET(TMDB.POPULAR_SHOWS)
    fun getPopularTvShows(): Observable<PagedList<TvShow>>

    @GET(TMDB.TODAY_SHOWS)
    fun getAiringTodayShows(): Observable<PagedList<TvShow>>

    @GET(TMDB.TV_DETAILS)
    fun getTvDetails(@Path("tv_id") tv_id: Int): Observable<TvShowDetail>
}