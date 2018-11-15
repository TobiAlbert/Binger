package ng.max.binger.services

import android.app.IntentService
import android.content.Intent
import ng.max.binger.data.AppDatabase
import ng.max.binger.data.FavoriteShow
import ng.max.binger.data.FavoriteShowDao
import ng.max.binger.data.TvShowDetail
import ng.max.binger.data.remote.ApiClient

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class to handle synchronization
 */
class SyncService : IntentService("SyncService") {

    private lateinit  var favDao: FavoriteShowDao

    override fun onCreate() {
        super.onCreate()
        favDao = AppDatabase.getInstance(this).favoriteDao()
    }

    override fun onHandleIntent(intent: Intent?) {
        // get favorites first
        favDao.getFavorites()
                .subscribe({
                    tvShows: List<FavoriteShow> ->

                    if (tvShows.isEmpty()) {
                        return@subscribe
                    }

                    tvShows.forEach {
                        tvShow: FavoriteShow ->
                        ApiClient.getTvShowService()
                                .getTvDetails(tvShow.id)
                                .subscribe( {
                                    tvShowDetail: TvShowDetail? ->
                                    if (tvShowDetail != null) {
                                        val favoriteShow = FavoriteShow().apply {
                                            id = tvShowDetail.id
                                            tvShowId = tvShowDetail.id
                                            name = tvShowDetail.name
                                            rating = tvShowDetail.rating
                                            summary = tvShowDetail.summary
                                            voteCount = tvShowDetail.voteCount
                                            posterPath = tvShowDetail.posterPath
                                            backdropPath = tvShowDetail.backdropPath
                                            latestSeason = tvShowDetail.numberOfSeasons
                                            latestEpisode = tvShowDetail.numberOfEpisodes

                                        }

                                        favDao.insertFavorite(favoriteShow)

                                    }
                                }) {

                                }
                    }
                }) {

                }
        TODO("Handle action Sync")
    }

}
