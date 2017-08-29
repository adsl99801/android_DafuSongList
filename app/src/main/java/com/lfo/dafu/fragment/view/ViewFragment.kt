package com.lfo.dafu.fragment.view

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.lfo.dafu.BaseFragment
import com.lfo.dafu.R
import com.lfo.dafu.dao.SongDao
import com.lfo.dafu.tool.FragmentHandler
import kotlinx.android.synthetic.main.fragment_viewfragment.*
import kotlinx.android.synthetic.main.toolbar_viewfrgment.view.*



/**
 * Created by home on 2017/8/15.
 */
class ViewFragment :BaseFragment(),Contract.IView{

    val SongIdIndex:String="SongIdIndex"
    override fun setToolBar(toolbar: Toolbar) {
        toolbar.removeAllViews()
        var view=LayoutInflater.from(context).inflate(R.layout.toolbar_viewfrgment, toolbar,true)
        view.igCheck.setOnClickListener({
            presenter.clickRightIcon()
            FragmentHandler.instance.backKeyPressd()
        })
    }


    private lateinit var presenter:Contract.IPresenter
    private val TypeIndex="Type"
    enum class Type{
        View,Edit,New
    }

    fun  newInstance(type : Type,songId:String): ViewFragment {
        var fragment = ViewFragment().apply {
            arguments=Bundle().apply {
                putInt(TypeIndex,type.ordinal)
                putString(SongIdIndex,songId)
            }
        }
        return  fragment
    }

    override fun genPresenter() {
        var type=arguments.getInt(TypeIndex,0)
        presenter=when(type){
            Type.View.ordinal-> BasePresenter(this, SongDao())
            Type.Edit.ordinal-> EditPresenter(this, SongDao())
            Type.New.ordinal-> BasePresenter(this, SongDao())
            else->{
                BasePresenter(this, SongDao())

            }
        }
    }

    override fun getModel(): Contract.Model {
        val num=etNum.text?.toString()?:""
        val name=etName.text?.toString()?:""
        val singer=etSinger.text?.toString()?:""
        val machine=etMachine.text?.toString()?:""
        return  Contract.Model(arguments.getString(SongIdIndex),num,name,singer,machine)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        genPresenter()
    }

    override fun onResume() {
        super.onResume()
        setEtMachine()
        setEtSinger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_viewfragment, container, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun setEtSinger() {

        var adapter =  ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, presenter.genSingerStrs())
        etSinger.setAdapter(adapter)
    }


    override fun setEtMachine() {
        var adapter =  ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, presenter.genMachineStrs())
        etMachine.setAdapter(adapter)
    }

}