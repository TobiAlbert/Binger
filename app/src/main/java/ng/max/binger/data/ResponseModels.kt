package ng.max.binger.data

import com.google.gson.annotations.SerializedName
import java.util.*


class PagedList<T> {
    var page = 0

    var results: List<T> = mutableListOf()

    var totalResults = 0

    var totalPages = 0
}

open class TvShow {

    var id = 0

    var name = ""

    @SerializedName("vote_average")
    var rating = 0f

    @SerializedName("overview")
    var summary = ""

    var voteCount = ""

    @SerializedName("poster_path")
    var posterPath = ""

    var backdropPath = ""

    @SerializedName("first_air_date")
    var firstAirDate: Date? = Date()

    var isLiked: Boolean = false
}

open class Genre {
    var id = 0

    var name = ""
}

class Producer: Genre()


class ProductionCompany {
    var id = 0

    var name = ""

    var logoPath: String? = ""

    var originCountry = ""
}


class Season {
    var id = 0

    var name = ""

    var summary = ""

    var airDate = Date()

    var episodeCount = 0

    var posterPath = ""

    var seasonNumber = 0
}

class TvShowDetail: TvShow() {

    var producers = ArrayList<Producer>()

    var episodeCount = ArrayList<Int>()

    var genres = ArrayList<Genre>()

    var inProduction = false

    var numberOfEpisodes = 0

    var numberOfSeasons = 0

    var productionCompanies = ArrayList<ProductionCompany>()

    var seasons = ArrayList<Season>()

    var status = ""

}