package ng.max.binger.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface FavoriteShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteShow: FavoriteShow): Long

    @Query("DELETE FROM favorite_show WHERE tv_show_id= :id")
    fun deleteFavorite(id: Int): Int

    @Query("SELECT * from favorite_show")
    fun getFavorites(): Flowable<List<FavoriteShow>>
}