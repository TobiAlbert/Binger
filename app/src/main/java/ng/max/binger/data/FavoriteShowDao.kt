package ng.max.binger.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Observable

@Dao
interface FavoriteShowDao {

    @Insert
    fun insertFavorite(favoriteShow: FavoriteShow)

    @Delete
    fun deleteFavorite(id: Int)

    @Query("SELECT * from favorite_show")
    fun getFavorites(): Observable<List<FavoriteShow>>
}