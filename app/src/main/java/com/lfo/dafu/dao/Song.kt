package com.lfo.dafu.dao

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


/**
 * Created by home on 2017/8/13.
 */
open class Song : RealmObject(){
    @PrimaryKey
    var id :String= UUID.randomUUID().toString()
    var number :String=""
    var name:String=""
    var singerId:String=""
    var machineId:String=""
}

