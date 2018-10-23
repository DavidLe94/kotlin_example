package com.android.haule.kotlinandroidexample.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.haule.kotlinandroidexample.R
import com.android.haule.kotlinandroidexample.api.ApiServices
import com.android.haule.kotlinandroidexample.api.ApiUtils
import com.android.haule.kotlinandroidexample.callbacks.ItemOnClickListener
import com.android.haule.kotlinandroidexample.eventbus.OwnerDetailEvent
import com.android.haule.kotlinandroidexample.models.Item
import com.android.haule.kotlinandroidexample.views.activity.DetailActivity
import com.android.haule.kotlinandroidexample.views.adapter.OwnerAdapter
import org.greenrobot.eventbus.EventBus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Hau Le on 2018-10-23.
 */
class MainController(context: Context): ItemOnClickListener{

    private var apiService: ApiServices? = null
    private var context: Context? = context
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var adapter: OwnerAdapter? = null
    private var list: ArrayList<Item>? = null

    init {
        apiService = ApiUtils.apiService
        recyclerView = (context as Activity).findViewById(R.id.list_owner)
        progressBar = context.findViewById(R.id.progress_bar)
        list = ArrayList()
        adapter = OwnerAdapter(context, this.list!!,this)
        this.recyclerView!!.adapter = adapter
    }

    fun callApi(){
        apiService!!.getAnswerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> onSuccess(result.items as ArrayList<Item>?)},
                    {error -> onFailed(error.message.toString())}
                )
    }

    private fun onSuccess(list: ArrayList<Item>?){
        this.list = list
        recyclerView!!.visibility = View.VISIBLE
        adapter!!.notifyData(list!!)
        progressBar!!.visibility = View.GONE
    }

    private fun onFailed(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(position: Int) {
        //send event bus
        EventBus.getDefault().postSticky(OwnerDetailEvent(this.list!![position]))
        context!!.startActivity(Intent(context, DetailActivity::class.java))
    }
}