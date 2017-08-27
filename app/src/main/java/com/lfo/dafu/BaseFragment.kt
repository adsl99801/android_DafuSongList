package com.lfo.dafu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by home on 2017/8/16.
 */
abstract class BaseFragment : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    abstract fun setToolBar(toolbar: Toolbar)

    override fun onResume() {
        super.onResume()
        setToolBar(activity.toolbar!!)

    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}