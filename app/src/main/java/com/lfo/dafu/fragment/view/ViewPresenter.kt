package com.lfo.dafu.fragment.view

import com.lfo.dafu.dao.SongDao
import com.lfo.dafu.tool.FragmentHandler

/**
 * Created by home on 2017/8/24.
 */
class ViewPresenter(var view:Contract.IView ,var dao: SongDao)  :Contract.IPresenter {
    override fun clickRightIcon() {
        var model=view.getModel()
        dao.saveSond(model)


    }


}