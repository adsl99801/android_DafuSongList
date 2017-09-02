package com.lfo.dafu

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager





/**
 * Created by home on 2017/8/13.
 */
class MApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        FlowManager.init(FlowConfig.Builder(this).build())

        Stetho.initializeWithDefaults(this);
        Logger.addLogAdapter(AndroidLogAdapter())

    }
    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
    private fun getEncryptionKey(): ByteArray {
        val name="dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"dafu_euip_"+"1234";
        return name.toByteArray()
    }
}