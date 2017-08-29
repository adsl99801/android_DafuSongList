package com.lfo.dafu.fragment.view

/**
 * Created by home on 2017/8/24.
 */
interface Contract {
    data class Model(var songId:String,var num:String,var name:String,var singer:String,var machine:String)
    interface IPresenter{
        fun clickRightIcon()
        fun genSingerStrs():List<String>
        fun genMachineStrs():List<String>

    }
    interface IView  {
        fun genPresenter()
        fun getModel(): Model
        fun setEtSinger()
        fun setEtMachine()

    }
}