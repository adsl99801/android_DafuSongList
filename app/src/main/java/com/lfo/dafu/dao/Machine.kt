package com.lfo.dafu.dao

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table


/**
 * Created by home on 2017/8/13.
 */

@Table(database = MDatabase::class)
data class Machine(@PrimaryKey(autoincrement = true)  var id: Long = 0, @Column var name: String="")