package com.lfo.dafu.fragment.readList

import com.lfo.dafu.adapter.ReadAdapter
import com.lfo.dafu.dao.SongDao

/**
 * Created by home on 2017/8/26.
 */
interface Contract {
    interface IView {
        fun genPresenter():IPresenter
    }
    interface IPresenter{
        fun setAdapter(adpater: ReadAdapter)
    }

}
class Presenter(var dao: SongDao): Contract.IPresenter {
    override fun setAdapter( adpater: ReadAdapter) {
        adpater.mlist=dao.getReadVos()
    }
}