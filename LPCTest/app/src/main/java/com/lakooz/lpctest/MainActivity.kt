package com.lakooz.lpctest

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lakooz.lpctest.databinding.ActivityMainBinding
import com.lakooz.lpctest.networking.RestApiClient
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.get
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding : ActivityMainBinding

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO : set content view and declare views
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewPager2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        setSupportActionBar(toolbar)

        TabLayoutMediator(tab_layout, viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                // TODO : set tabs titles
                if (position == 0){tab.setText(getString(R.string.birthday));}
                if (position == 1){tab.setText(getString(R.string.farewell));}
                if (position == 2){tab.setText(getString(R.string.solidarity));}
            }).attach()


       swipe_refresh_layout.setProgressViewOffset(true, START_SWIPE_REFRESH, resources.getDimension(R.dimen.swipe_refresh_offset).toInt())

        // TODO : set up view model
        println("testConnection: "+isOnline(baseContext))

        if (isOnline(baseContext))
        {
            MainViewModel(application).getPots()

        }
        else
        {
            Toast.makeText(baseContext, "No Connection", Toast.LENGTH_LONG)          }

        swipe_refresh_layout.setOnRefreshListener {
            // TODO
            println("testConnection2: "+isOnline(baseContext))
            if (isOnline(baseContext))
            {
                println("Connection exist")
                MainViewModel(application).getPots()
                viewPager2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)


                swipe_refresh_layout.isRefreshing = false

            }
            else
            {
                println("No Connection!")
                Toast.makeText(baseContext, "No Connection", Toast.LENGTH_LONG)
              viewPager2.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
               setSupportActionBar(toolbar)

                TabLayoutMediator(tab_layout, viewPager2,
                    TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                        // TODO : set tabs titles
                        if (position == 0){tab.setText(getString(R.string.birthday));}
                        if (position == 1){tab.setText(getString(R.string.farewell));}
                        if (position == 2){tab.setText(getString(R.string.solidarity));}
                    }).attach()
                swipe_refresh_layout.isRefreshing = false

            }

        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                val viewPagerIdle = state == ViewPager2.SCROLL_STATE_IDLE
                swipe_refresh_layout.isEnabled = viewPagerIdle
            }
        })

        fab.setOnClickListener {
            // TODO
            MainViewModel(application).createPot(2)
            MainViewModel(application).getPots()


        }
    }

    companion object {
        private const val START_SWIPE_REFRESH = 50
    }
    @SuppressLint("MissingPermission")
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
