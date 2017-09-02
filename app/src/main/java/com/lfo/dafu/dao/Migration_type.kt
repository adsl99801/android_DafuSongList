package com.lfo.dafu.dao

import com.raizlabs.android.dbflow.annotation.Migration
import com.raizlabs.android.dbflow.sql.SQLiteType
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration

/**
 * Created by home on 2017/9/3.
 */
@Migration(version = 2, database = MDatabase::class)
class Migration_type : AlterTableMigration<Song>(Song::class.java) {
    override fun onPreMigrate() {
        super.onPreMigrate()
        addColumn(SQLiteType.TEXT,"type")
    }
}