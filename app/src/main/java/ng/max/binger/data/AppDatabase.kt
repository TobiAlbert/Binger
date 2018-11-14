package ng.max.binger.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [FavoriteShow::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val favoriteDao: FavoriteShowDao

    companion object {

        private const val DATABASE_NAME = "binger.db"

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigrationFrom(1)
                        .build()

    }

}