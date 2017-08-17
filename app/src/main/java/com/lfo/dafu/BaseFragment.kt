package com.lfo.dafu

import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by home on 2017/8/16.
 */
abstract class BaseFragment : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolBar(activity.toolbar)
    }

    override fun onResume() {
        super.onResume()

    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    abstract  fun setToolBar(toolBar:android.support.v7.widget.Toolbar )
}