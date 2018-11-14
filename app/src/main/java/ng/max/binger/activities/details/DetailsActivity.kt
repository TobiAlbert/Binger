package ng.max.binger.activities.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import ng.max.binger.R
import ng.max.binger.adapters.GenreAdapter
import ng.max.binger.adapters.ProductionCompanyAdapter
import ng.max.binger.data.TvShowDetail
import ng.max.binger.data.TvShowRepository
import ng.max.binger.data.remote.ApiClient
import ng.max.binger.data.remote.CloudTvShowRepository
import ng.max.binger.utils.DisplayUtils

class DetailsActivity : AppCompatActivity(), DetailsContract.View {

    private lateinit var mPresenter: DetailsContract.Presenter
    private lateinit var mRepository: TvShowRepository
    private lateinit var mToast: Toast
    private lateinit var mMoviePosterImageView: ImageView
    private lateinit var mMovieTitleTextView: TextView
    private lateinit var mMovieYearTextView: TextView
    private lateinit var mMovieSummary: TextView
    private lateinit var mMovieStatus: TextView
    private lateinit var mGenreRecyclerView: RecyclerView
    private lateinit var mProductionCompanyRecyclerView: RecyclerView
    private lateinit var mGenreAdapter: GenreAdapter
    private lateinit var mProductionAdapter: ProductionCompanyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // set support toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvId = intent?.getIntExtra("tvId",  0)

        val message = "Loading"
        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG)

        mMoviePosterImageView = moviePoster
        mMovieTitleTextView = movieTitle
        mMovieYearTextView = movieYear
        mMovieSummary = movieSummary
        mMovieStatus = showStatus

        mGenreAdapter = GenreAdapter()
        mGenreRecyclerView = recyclerview_genres.apply {
            adapter = mGenreAdapter
            layoutManager = LinearLayoutManager(
                    this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        mProductionAdapter = ProductionCompanyAdapter()
        mProductionCompanyRecyclerView = recyclerview_production_companies.apply {
            adapter = mProductionAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }



        mRepository = CloudTvShowRepository(ApiClient.getTvShowService())
        mPresenter = DetailsPresenter(mRepository)
        mPresenter.attachView(this)

        mPresenter.getTvShowDetails(tvId ?: 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun displayTvShow(tvShow: TvShowDetail) {
        Glide.with(this@DetailsActivity)
                .load(DisplayUtils.getImageUrl(tvShow.posterPath))
                .into(mMoviePosterImageView)

        mMovieTitleTextView.text = tvShow.name
        mMovieYearTextView.text = DisplayUtils.getYear(tvShow.firstAirDate)
        mMovieSummary.text = tvShow.summary
        mMovieStatus.text = tvShow.status

        mGenreAdapter.tvGenres = tvShow.genres
        mProductionAdapter.companyList = tvShow.productionCompanies

    }

    override fun showLoading() {
        mToast.show()
    }

    override fun hideLoading() {
        mToast.cancel()
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
                .show()
    }
}
