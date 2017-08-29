package com.lfo.dafu.dao

import com.google.gson.Gson
import com.lfo.dafu.fragment.view.Contract
import com.lfo.dafu.vo.ReadVo
import com.orhanobut.logger.Logger
import io.realm.Realm
import java.util.*

/**
 * Created by home on 2017/8/24.
 */
class SongDao {
    fun getReadVos():MutableList<ReadVo>{
        val realm = Realm.getDefaultInstance()
        var songs = realm.where(Song::class.java).findAll()
        var songs1 = realm.copyFromRealm(songs)
        val gson = Gson()
        Logger.json( gson.toJson(songs1))
        var mlist =songs.map {song->
            ReadVo(number = song.number, name = song.name)
        }.toMutableList()
        return mlist;
    }
    fun saveSond( model: Contract.Model){
        var realm= Realm.getDefaultInstance()
        realm.beginTransaction()


        var songOb =realm.createObject(Song::class.java, UUID.randomUUID().toString())
        songOb.number=model.num
        songOb.name=model.name

        var query=realm.where(Singer::class.java)
        var firstSinger=query.equalTo("name",model.singer).findFirst()
        var singerId:String
        if(firstSinger==null){
            singerId = UUID.randomUUID().toString()
            firstSinger =realm.createObject(Singer::class.java, singerId)
        }
        singerId=firstSinger.id
        firstSinger.name=model.singer
        firstSinger.songs.add(songOb)


        var query1=realm.where(Machine::class.java)
        var firstMachine=query1.equalTo("name",model.machine).findFirst()
        var machineId:String
        if(firstMachine==null){
            machineId = UUID.randomUUID().toString()
            firstMachine =realm.createObject(Machine::class.java, machineId)
        }
        firstMachine.name=model.machine
        firstMachine.songs.add(songOb)
        machineId=firstMachine.id
        songOb.singerId=singerId
        songOb.machineId=machineId
        realm.commitTransaction()
    }
    fun updateSong(model: Contract.Model){
        var realm= Realm.getDefaultInstance()
        var song=realm.where(Song::class.java).equalTo("id",model.songId).findFirst()
        if(song==null){
            Logger.w("first==null")
            return
        }
        realm.beginTransaction()
        var oldSinger=realm.where(Singer::class.java).equalTo("id",song.singerId).findFirst()
        if(oldSinger!=null){
            oldSinger.songs.remove(song)
        }


        var firstSinger=realm.where(Singer::class.java).equalTo("name",model.singer).findFirst()
        if(firstSinger==null){
            firstSinger=realm.createObject(Singer::class.java, UUID.randomUUID().toString())
        }
        firstSinger.songs.add(song)


        var oldM=realm.where(Machine::class.java).equalTo("id",song.machineId).findFirst()
        if(oldM!=null){
            oldM.songs.remove(song)
        }
        var firstMachine=realm.where(Machine::class.java).equalTo("name",model.machine).findFirst()
        if(firstMachine==null){
            firstMachine=realm.createObject(Machine::class.java, UUID.randomUUID().toString())
        }
        firstMachine.songs.add(song)

        song.name=model.name

        realm.commitTransaction()
    }

    fun allSigerStrs(): List<String> {
        var realm= Realm.getDefaultInstance()
         return realm.where(Singer::class.java).findAll().map { x->x.name }.toList()
    }
    fun allMachineStrs(): List<String> {
        var realm= Realm.getDefaultInstance()
        return realm.where(Machine::class.java).findAll().map { x->x.name }.toList()
    }
}