package ng.max.binger.data

import io.reactivex.Observable

class FavoriteRepository(private val favoriteShowDao: FavoriteShowDao):
        TvShowRepository.FavoriteRepository {

    override fun getFavoriteTvShows(): Observable<List<TvShowDetail>> {
        val favoriteTvShowList = arrayListOf<TvShowDetail>()
        return favoriteShowDao.getFavorites()
                .concatMap {
                    favoriteTvShows: List<FavoriteShow> ->
                    for (favoriteTvShow in favoriteTvShows) {
                        val tvShow = TvShowDetail().apply {
                            id = favoriteTvShow.tvShowId
                            name = favoriteTvShow.name
                            summary = favoriteTvShow.summary
                            rating = favoriteTvShow.rating
                            voteCount = favoriteTvShow.voteCount
                            posterPath = favoriteTvShow.posterPath
                            backdropPath = favoriteTvShow.backdropPath
                            numberOfSeasons = favoriteTvShow.latestSeason
                            numberOfEpisodes = favoriteTvShow.latestEpisode
                        }
                        favoriteTvShowList.add(tvShow)
                    }

                    Observable.fromArray(favoriteTvShowList)
                }

    }
}