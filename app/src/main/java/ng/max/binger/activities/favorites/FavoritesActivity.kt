package ng.max.binger.activities.favorites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_favorites.*
import ng.max.binger.R
import ng.max.binger.adapters.favorite.FavoritesAdapter
import ng.max.binger.adapters.tvshow.TvShowsAdapter
import ng.max.binger.data.*
import ng.max.binger.utils.gone
import ng.max.binger.utils.invisible
import ng.max.binger.utils.visible

class FavoritesActivity : AppCompatActivity(), FavouritesContract.View {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FavoritesAdapter
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

        mAdapter = FavoritesAdapter()

        mRecyclerView = tvShowsFavorite.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
        }

        val repository = FavoriteRepository(applicationContext)
        mPresenter = FavoritesPresenter(repository)
        mPresenter.attachView(this)
        mPresenter.getShows()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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

    override fun showShows(tvShows: List<TvShow>) {
        val temp = ArrayList<TvShow>(tvShows)
        mAdapter.tvShows = temp
    }
}
