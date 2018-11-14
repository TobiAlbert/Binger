package ng.max.binger.data

import android.arch.persistence.room.*
import java.util.*


@Entity(tableName = "favorite_show", indices = [(Index("id", unique = true))])
data class FavoriteShow(@PrimaryKey(autoGenerate = false)
                        @ColumnInfo(name = "id")
                        var id: Int = 0,

                        @ColumnInfo(name = "tv_show_id")
                        var tvShowId: Int = 0,

                        @ColumnInfo(name = "name")
                        var name: String = "",

                        @ColumnInfo(name = "rating")
                        var rating: Float = 0f,

                        @ColumnInfo(name = "summary")
                        var summary: String = "",

                        @ColumnInfo(name = "vote_count")
                        var voteCount: String = "",

                        @ColumnInfo(name = "poster_path")
                        var posterPath: String = "",

                        @ColumnInfo(name = "backdrop_path")
                        var backdropPath: String = "",

                        @ColumnInfo(name = "latest_season")
                        var latestSeason: Int = 0,

                        @ColumnInfo(name = "latest_episode")
                        var latestEpisode: Int = 0,

                        @ColumnInfo(name = "first_air_date")
                        var firstAirDate: Date? = Date())