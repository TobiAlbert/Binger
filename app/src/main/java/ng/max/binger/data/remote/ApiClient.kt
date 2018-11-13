package ng.max.binger.data.remote

import ng.max.binger.utils.TMDB
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        private var mOkHttpClient: OkHttpClient? = null
        private var mRetrofitInstance: Retrofit? = null
        private var mTvShowService: TvShowService? = null

        private fun getRetrofitClient(): Retrofit {

            if (mRetrofitInstance == null) {
                val retrofit = Retrofit.Builder().client(getOkHttpClient())
                        .baseUrl(TMDB.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                mRetrofitInstance = retrofit.build()
            }

            return mRetrofitInstance!!
        }

        private fun getOkHttpClient(): OkHttpClient {

            if (mOkHttpClient == null) {
                mOkHttpClient = OkHttpClient.Builder()
                        .addInterceptor {
                            chain: Interceptor.Chain ->
                            val httpUrl = chain.request().url()
                            val newUrl = httpUrl.newBuilder()
                                    .addQueryParameter("api_key", TMDB.API_KEY_VALUE)
                                    .build()

                            val newRequestBuilder = chain.request().newBuilder()
                                    .url(newUrl)
                            newRequestBuilder.header(
                                    "Content-Type", "application/json"
                            )
                            chain.proceed(newRequestBuilder.build())
                        }.build()
            }

            return mOkHttpClient!!
        }

        fun getTvShowService(): TvShowService {
            if (mTvShowService == null) {
                mTvShowService = getRetrofitClient().create(TvShowService::class.java)
            }

            return mTvShowService!!
        }
    }



}

