package com.lfo.dafu.fragment.view

/**
 * Created by home on 2017/8/24.
 */
interface Contract {
    data class Model(var num:String,var name:String,var singer:String,var machine:String)
    interface IPresenter{
        fun clickRightIcon()

    }
    interface IView  {
        fun genPresenter()
        fun getModel(): Model

    }
}