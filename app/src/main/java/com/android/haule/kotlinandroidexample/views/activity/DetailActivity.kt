package com.android.haule.kotlinandroidexample.views.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.haule.kotlinandroidexample.R
import com.android.haule.kotlinandroidexample.eventbus.OwnerDetailEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DetailActivity : AppCompatActivity(), View.OnClickListener{
    var TAG: String = "DetailActivity"
    
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.img_profile_image -> {
                Log.d(TAG, " Image onClick")
            }
            R.id.tv_display_name -> {
                Log.d(TAG, " Name onClick")
            }
            R.id.tv_user_type -> {
                Log.d(TAG, " User Type onClick")
            }
        }
    }

    private var profileImage: CircleImageView? = null
    private var displayName: TextView? = null
    private var userType: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        setEvent()
    }

    private fun setEvent() {
        profileImage!!.setOnClickListener(this)
        displayName!!.setOnClickListener(this)
        userType!!.setOnClickListener(this)
    }

    private fun initView() {
        profileImage = findViewById(R.id.img_profile_image)
        displayName = findViewById(R.id.tv_display_name)
        userType = findViewById(R.id.tv_user_type)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onReceivedEvent(event: OwnerDetailEvent){
        displayName!!.text = event.getItem()!!.owner!!.displayName
        userType!!.text = event.getItem()!!.owner!!.userType
        Glide.with(this)
                .load(event.getItem()!!.owner!!.profileImage)
                .apply(RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(this.profileImage!!)
        //remove event after use done
        EventBus.getDefault().removeStickyEvent(event)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
