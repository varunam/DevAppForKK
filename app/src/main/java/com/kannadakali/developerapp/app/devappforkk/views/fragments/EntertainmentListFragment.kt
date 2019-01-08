package com.kannadakali.developerapp.app.devappforkk.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kannadakali.developerapp.app.devappforkk.R

/**
 * Created by varun.am on 08/01/19
 */
class EntertainmentListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance(): EntertainmentListFragment {

            val args = Bundle()

            val fragment = EntertainmentListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.layout_entertainment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_id)
    }
}