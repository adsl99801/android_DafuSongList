package com.lfo.dafu.fragment

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfo.dafu.BaseFragment
import com.lfo.dafu.R
import com.lfo.dafu.tool.FragmentHandler
import kotlinx.android.synthetic.main.toolbar_viewfrgment.view.*

/**
 * Created by home on 2017/8/15.
 */
class ViewFragment : BaseFragment(){
    override fun setToolBar(toolBar: Toolbar) {
        toolBar.removeAllViews()
        var view=LayoutInflater.from(context).inflate(R.layout.toolbar_viewfrgment, toolBar,true)
        view.igCheck.setOnClickListener({
            save()
            FragmentHandler.instance.backKeyPressd()
        })
    }

    fun save(){
        
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewfragment, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}