package ng.max.binger.data

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.Maybe

class FavoriteRepository(val context: Context):
        TvShowRepository.FavoriteRepository {

    private var mFavShowDao: FavoriteShowDao = AppDatabase.getInstance(context).favoriteDao()

    override fun getFavoriteTvShows(): Flowable<List<TvShow>> {
        val favoriteTvShowList = arrayListOf<TvShow>()
        return mFavShowDao.getFavorites()
                .concatMap {
                    favoriteTvShows: List<FavoriteShow> ->
                    for (favoriteTvShow in favoriteTvShows) {
                        val tvShow = TvShow().apply {
                            id = favoriteTvShow.tvShowId
                            name = favoriteTvShow.name
                            summary = favoriteTvShow.summary
                            rating = favoriteTvShow.rating
                            voteCount = favoriteTvShow.voteCount
                            posterPath = favoriteTvShow.posterPath
                            backdropPath = favoriteTvShow.backdropPath
                            isLiked = true
                        }
                        favoriteTvShowList.add(tvShow)
                    }

                    Flowable.fromArray(favoriteTvShowList)
                }
    }

    override fun insertTvShow(favoriteShow: FavoriteShow): Maybe<Long> {
        return Maybe.fromAction {
            mFavShowDao.insertFavorite(favoriteShow)
        }
    }

    override fun deleteTvShow(id: Int): Maybe<Int> {
        return Maybe.fromAction {
            mFavShowDao.deleteFavorite(id)
        }
    }
}