package com.lfo.dafu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.lfo.dafu.fragment.MainFragment
import com.lfo.dafu.tool.FragmentHandler
import com.lfo.dafu.vo.event.FBAddEvent
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.simple.eventbus.EventBus


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.visibility= View.GONE
        fab.setOnClickListener {
//            val realm = Realm.getDefaultInstance()
//            realm.beginTransaction()
//
//            var song1 =realm.createObject(Song::class.java,UUID.randomUUID().toString())
//            song1.name="name1"
//            song1.number="number1"
//            val singer =    realm.createObject(Singer::class.java,UUID.randomUUID().toString())
//            singer.name="singer1"
//            singer.songs.add(song1)
//
//            val machine =  realm.createObject(Machine::class.java,UUID.randomUUID().toString())
//            machine.name="machine1"
//            machine.songs.add(song1)
//
//
//            realm.commitTransaction()
            EventBus.getDefault().post(FBAddEvent())

        }

        FragmentHandler.instance.prepare(R.id.container,supportFragmentManager)
        FragmentHandler.instance.to(MainFragment())
//        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val myActionMenuItem=menu.findItem(R.id.action_search)
        var searchView = myActionMenuItem.actionView as android.support.v7.widget.SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                Logger.i("query:"+query)
                if (!searchView.isIconified()) {
                    searchView.setIconified(true)
                }
                myActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false
            }
        })
        return true
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String

//    companion object {
//
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}
