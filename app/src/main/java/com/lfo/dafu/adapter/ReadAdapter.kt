package com.lfo.dafu.adapter;

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfo.dafu.R
import com.lfo.dafu.vo.ReadVo
import kotlinx.android.synthetic.main.adapter_readadapter.view.*


/**
 * Created by home on 2017/8/13.
 */

class ReadAdapter : RecyclerView.Adapter<ReadAdapter.ViewHolder>() {
    @LayoutRes
    val layout= R.layout.adapter_readadapter
    var mlist = mutableListOf<ReadVo>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setValue(mlist.get(position))
    }

    override fun getItemCount(): Int {
       return mlist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setValue(entity: ReadVo) {
            itemView.tvNum.setText(entity.number)
            itemView.tvName.setText(entity.name)

        }
    }
}
