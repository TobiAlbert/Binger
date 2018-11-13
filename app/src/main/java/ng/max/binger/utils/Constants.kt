package ng.max.binger.utils

object TMDB {
    // api key
    const val API_KEY_VALUE = "b3949470a8e2eb48fdded7ea54ea27df"
    const val  DEFAULT_PAGE_SIZE = 20 // default page_size for TMDB pagination
    const val API_KEY = "api_key=$API_KEY_VALUE"

    // url endpoints
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POPULAR_SHOWS = "${BASE_URL}tv/popular"
    const val TODAY_SHOWS = "${BASE_URL}tv/airing_today"
}