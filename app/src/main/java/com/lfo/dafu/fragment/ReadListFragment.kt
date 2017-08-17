package com.lfo.dafu.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.lfo.dafu.BaseFragment
import com.lfo.dafu.R
import com.lfo.dafu.adapter.ReadAdapter
import com.lfo.dafu.dao.Song
import com.lfo.dafu.tool.FragmentHandler
import com.lfo.dafu.vo.ReadVo
import com.lfo.dafu.vo.event.FBAddEvent
import com.lfo.dafu.vo.event.ReadRefreshEvent
import com.orhanobut.logger.Logger
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_readlistfragment.*
import kotlinx.android.synthetic.main.toolbar_readlistfragment.view.*
import org.simple.eventbus.EventBus
import org.simple.eventbus.Subscriber


/**
 * A placeholder fragment containing a simple view.
 */
class ReadListFragment : BaseFragment() {
    var readAdapter = ReadAdapter()
    override fun setToolBar(toolBar: Toolbar) {
        toolBar.removeAllViews()
        var view=LayoutInflater.from(context).inflate(R.layout.toolbar_readlistfragment, toolBar,true)
        view.igAdd.setOnClickListener({ FragmentHandler.instance.toByHideFrom(ViewFragment())})

    }

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
        resumeFun()

    }

    private fun resumeFun() {
        val realm = Realm.getDefaultInstance()
        var songs = realm.where(Song::class.java).findAll()
        var songs1 = realm.copyFromRealm(songs)
        val gson = Gson()
        Logger.json( gson.toJson(songs1))
        readAdapter.mlist =songs.map {song->
           ReadVo(number = song.number, name = song.name)
        }.toMutableList()
        readAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
    private fun ReadRefreshEvent (readRefreshEvent : ReadRefreshEvent){
        resumeFun()
    }
    @Subscriber
    private fun FBAddEvent(FBAddEvent: FBAddEvent){
        FragmentHandler.instance.toByHideFrom(ViewFragment())
    }
}
