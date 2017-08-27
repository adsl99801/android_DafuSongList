package com.lfo.dafu.fragment

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfo.dafu.BaseFragment
import com.lfo.dafu.R
import com.lfo.dafu.fragment.readList.ReadListFragment
import com.lfo.dafu.tool.FragmentHandler
import kotlinx.android.synthetic.main.fragment_mainfragment.*

/**
 * Created by home on 2017/8/15.
 */
class MainFragment: BaseFragment() {
    override fun setToolBar(toolBar: Toolbar) {
        toolBar.removeAllViews()
        LayoutInflater.from(context).inflate(R.layout.toolbar_onlytitle, toolBar,true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        but1.setOnClickListener{
            FragmentHandler.instance.toByHideFrom(ReadListFragment())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mainfragment, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}