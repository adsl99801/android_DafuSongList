package com.lfo.dafu.tool

/**
 * Created by home on 2017/8/16.
 */
class ToolBarHandler private constructor(){
    private object Handler{val INSTANCE=ToolBarHandler()}
    companion object {
        val instance:ToolBarHandler by lazy { Handler.INSTANCE }
    }

}