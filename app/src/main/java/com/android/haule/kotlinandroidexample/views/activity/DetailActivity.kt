package com.android.haule.kotlinandroidexample.views.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.haule.kotlinandroidexample.R
import com.android.haule.kotlinandroidexample.eventbus.OwnerDetailEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onReceivedEvent(event: OwnerDetailEvent){
        Log.d("TAG", event.getItem()!!.owner!!.displayName)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
