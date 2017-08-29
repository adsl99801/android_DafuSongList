package com.lfo.dafu.fragment.view

import com.lfo.dafu.dao.SongDao

/**
 * Created by home on 2017/8/24.
 */
open class BasePresenter(var view:Contract.IView, var dao: SongDao)  :Contract.IPresenter {
    override fun genSingerStrs(): List<String> {
        return dao.allSigerStrs()
    }

    override fun genMachineStrs(): List<String> {
        return dao.allMachineStrs()
    }

    override fun clickRightIcon() {
        var model=view.getModel()
        dao.saveSond(model)
    }


}