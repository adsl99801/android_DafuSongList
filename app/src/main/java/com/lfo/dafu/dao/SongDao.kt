package com.lfo.dafu.dao

import com.lfo.dafu.fragment.view.Contract
import com.lfo.dafu.vo.ReadVo
import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.sql.language.SQLite


/**
 * Created by home on 2017/8/24.
 */
class SongDao {
    fun getReadVos(): MutableList<ReadVo> {
        return SQLite.select().from(Song::class.java).list.map { x-> ReadVo(number = x.number,name = x.name) }.toMutableList()
    }
    fun saveSong( model: Contract.Model){
        var machineId=SQLite.select().from(Machine::class.java).where(Machine_Table.name.eq(model.machine)).querySingle()?.id?:0
        var singerId=SQLite.select().from(Singer::class.java).where(Machine_Table.name.eq(model.singer)).querySingle()?.id?:0

        var  song = Song(number = model.name,name=model.name,singerId = singerId,machineId = machineId)
        song.save()
    }
    fun updateSong(model: Contract.Model){

    }

    fun allSigerStrs(): List<String> {
        return  SQLite.select().from(Song::class.java).list.map { x-> x.name}
    }
    fun allMachineStrs(): List<String> {
        return  SQLite.select().from(Machine::class.java).list.map { x-> x.name}
    }
}