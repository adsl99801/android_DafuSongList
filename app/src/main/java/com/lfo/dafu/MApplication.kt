package com.lfo.dafu

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration





/**
 * Created by home on 2017/8/13.
 */
class MApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("dafu.realm")
//                .encryptionKey(getEncryptionKey())
                .schemaVersion(1)
        var realmObject=config.build()
        Realm.setDefaultConfiguration(realmObject)

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
        Logger.addLogAdapter(AndroidLogAdapter())

    }

    private fun getEncryptionKey(): ByteArray {
        val name="dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"1234";
        return name.toByteArray()
    }
}