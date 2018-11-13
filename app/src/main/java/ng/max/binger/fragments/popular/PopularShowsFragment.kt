package ng.max.binger.fragments.popular

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ng.max.binger.R
import ng.max.binger.adapters.TvShowsAdapter


/**
 * A simple [Fragment] subclass.
 */

class PopularShowsFragment: Fragment() {

    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_popular_shows, container, false)

        // TODO: setup popular tv shows recycler view

        return root
    }

    companion object {
        fun newInstance() = PopularShowsFragment()
    }

}
