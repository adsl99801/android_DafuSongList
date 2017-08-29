package com.lfo.dafu.fragment.readList

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfo.dafu.BaseFragment
import com.lfo.dafu.R
import com.lfo.dafu.adapter.ReadAdapter
import com.lfo.dafu.dao.SongDao
import com.lfo.dafu.fragment.view.ViewFragment
import com.lfo.dafu.tool.FragmentHandler
import com.lfo.dafu.vo.event.FBAddEvent
import com.lfo.dafu.vo.event.ReadRefreshEvent
import kotlinx.android.synthetic.main.fragment_readlistfragment.*
import kotlinx.android.synthetic.main.toolbar_readlistfragment.view.*
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber


/**
 * A placeholder fragment containing a simple view.
 */

class ReadListFragment : BaseFragment(),Contract.IView{
    override fun setToolBar(toolbar: Toolbar) {
        toolbar.removeAllViews()
        var view=LayoutInflater.from(context).inflate(R.layout.toolbar_readlistfragment, toolbar,true)
        view.igAdd.setOnClickListener({ FragmentHandler.instance.toByHideFrom(ViewFragment().newInstance(ViewFragment.Type.New,""))})
    }


    private var readAdapter = ReadAdapter()
    private lateinit var presenter:Contract.IPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        EventBus.getDefault().register(this)
        return inflater.inflate(R.layout.fragment_readlistfragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val manager = LinearLayoutManager(context)
        manager.orientation=LinearLayoutManager.VERTICAL
        rv1.layoutManager=manager
        rv1.adapter = readAdapter

    }

    override fun onResume() {
        super.onResume()

        presenter=genPresenter()
        presenter.setAdapter(readAdapter)
        readAdapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
    private fun ReadRefreshEvent (readRefreshEvent : ReadRefreshEvent){
        onResume()
    }


    override fun genPresenter(): Contract.IPresenter {
        return Presenter(SongDao())
    }

    @Subscriber
    private fun FBAddEvent(FBAddEvent: FBAddEvent){
        FragmentHandler.instance.toByHideFrom(ViewFragment().newInstance(ViewFragment.Type.New,""))
    }
}
