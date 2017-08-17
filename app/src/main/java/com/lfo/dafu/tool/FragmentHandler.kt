package com.lfo.dafu.tool

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.lfo.dafu.fragment.MainFragment
import com.orhanobut.logger.Logger
import org.simple.eventbus.EventBus


/**
 * Created by home on 2017/8/15.
 */
class FragmentHandler private constructor(){
    init{}
    private object Handler{val INSTANCE=FragmentHandler()}
    companion object {
        val instance:FragmentHandler by lazy { Handler.INSTANCE }
    }

    private var recentFragmentContainer = -1
    private lateinit var recentFragmentManager: FragmentManager

    fun prepare(recentFragmentContainer: Int, recentFragmentManager: FragmentManager
    ) {

        EventBus.getDefault().registerSticky(this)
        this.recentFragmentContainer = recentFragmentContainer
        this.recentFragmentManager = recentFragmentManager
    }

    fun destory() {
        EventBus.getDefault().unregister(this)
    }

    fun popAll() {
        try {
            recentFragmentManager.popBackStack(null, 0)
            val count = recentFragmentManager.backStackEntryCount
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun popToMainFragment() {
        recentFragmentManager.popBackStackImmediate(MainFragment::class.java.name, 0)
        val count = recentFragmentManager.getBackStackEntryCount()
    }

    fun toByHideFrom(toFragment: Fragment) {

        val toFragmentTag = toFragment.javaClass.name
        val inStack = recentFragmentManager.findFragmentByTag(toFragmentTag)
        if (inStack != null) {
            if (inStack.isAdded) {
                Logger.i("isAdded!")
                return
            }
        }


        val transaction = recentFragmentManager.beginTransaction()
        transaction.addToBackStack(toFragmentTag)
        transaction.replace(recentFragmentContainer, toFragment, toFragmentTag)
        transaction.commitAllowingStateLoss()

//        DrawerTool.instance.setIndicatorImageAsBack()
//        sendTrack(toFragmentTag)


    }

    fun to(toFragment: Fragment) {
        popToMainFragment()
        if (toFragment == null) {
            Logger.i("to() toFragment is null")
            return
        }
        val toFragmentTag = toFragment.javaClass.name
        val inStack = recentFragmentManager.findFragmentByTag(toFragmentTag)
        if (inStack != null) {
            if (inStack.isAdded) {
                Logger.i("isAdded!")
                return
            }
        }
        popToMainFragment()
        val transaction = recentFragmentManager.beginTransaction()
        transaction.addToBackStack(toFragmentTag)
        transaction.replace(recentFragmentContainer, toFragment, toFragmentTag)
        transaction.commit()

//        DrawerTool.instance.setIndicatorImageAsMenu()
//        sendTrack(toFragmentTag)
    }

    fun backKeyPressd(): Boolean {
        var bool = false
        var count = recentFragmentManager.backStackEntryCount

        if (count > 1) {
            try {
                recentFragmentManager.popBackStack()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        count = recentFragmentManager.backStackEntryCount
//        if (count <= 2) {
//            DrawerTool.instance.setIndicatorImageAsMenu()
//        } else {
//            DrawerTool.instance.setIndicatorImageAsBack()
//        }
//
//        if (DrawerTool.instance.getDrawer() == null) {
//            KLog.i("DrawerTool.instance.getDrawer() == null)")
//            return false
//        }
//        if (count <= 1) {
//            if (!DrawerTool.instance.getDrawer().isDrawerOpen()) {
//                DrawerTool.instance.getDrawer().openDrawer()
//                bool = true
//            }
//        }

        return bool


    }
}