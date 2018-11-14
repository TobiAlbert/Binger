package ng.max.binger.activities.favorites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_favorites.*
import ng.max.binger.R
import ng.max.binger.adapters.TvShowsAdapter
import ng.max.binger.data.*
import ng.max.binger.utils.gone
import ng.max.binger.utils.invisible
import ng.max.binger.utils.visible

class FavoritesActivity : AppCompatActivity(), FavouritesContract.View {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: TvShowsAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mPresenter: FavouritesContract.Presenter

    companion object {
        var TAG = FavoritesActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // set support toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mProgressBar = progress_bar_favorite

        mAdapter = TvShowsAdapter()

        mRecyclerView = tvShowsFavorite.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
        }

        //TODO: Use DI to pass dependencies
        val repository = FavoriteRepository(AppDatabase.getInstance(this).favoriteDao)
        mPresenter = FavoritesPresenter(repository)
        mPresenter.attachView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showLoading() {
        mRecyclerView.invisible()
        mProgressBar.visible()
    }

    override fun hideLoading() {
        mRecyclerView.visible()
        mProgressBar.gone()
    }

    override fun showError(msg: String) {
        Log.w(TAG, msg)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
                .show()
    }

    override fun showShows(tvShows: List<TvShowDetail>) {
        mAdapter.tvShows = tvShows
    }
}
