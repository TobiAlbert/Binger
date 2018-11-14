package ng.max.binger.fragments.airing


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_airing_today.view.*
import ng.max.binger.R
import ng.max.binger.adapters.tvshow.TvShowsAdapter
import ng.max.binger.data.TvShow
import ng.max.binger.data.remote.ApiClient
import ng.max.binger.data.remote.CloudTvShowRepository
import ng.max.binger.fragments.FragmentMvpContract
import ng.max.binger.utils.gone
import ng.max.binger.utils.invisible
import ng.max.binger.utils.visible

/**
 * A simple [Fragment] subclass.
 */

class AiringTodayFragment : Fragment(), FragmentMvpContract.View {

    private lateinit var mAdapter: TvShowsAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPresenter: FragmentMvpContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_airing_today, container, false)

        mProgressBar = root.progress_bar_airing

        mAdapter = TvShowsAdapter()

        mRecyclerView = root.tvShowsToday.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val repository = CloudTvShowRepository(ApiClient.getTvShowService())

        mPresenter = AiringTodayPresenter(repository)
        mPresenter.attachView(this)
        mPresenter.getShows()

        return root
    }

    companion object {
        fun newInstance() = AiringTodayFragment()
        var TAG = AiringTodayFragment::class.java.simpleName
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
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
                .show()
    }

    override fun showShows(tvShows: List<TvShow>) {
        val temp = ArrayList<TvShow>(tvShows)
        mAdapter.tvShows = temp
    }

}
