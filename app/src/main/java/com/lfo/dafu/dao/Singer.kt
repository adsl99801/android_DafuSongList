package com.lfo.dafu.dao

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


/**
 * Created by home on 2017/8/13.
 */
open class Singer : RealmObject() {
    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var name:String=""
    var songs: RealmList<Song> = RealmList()


}