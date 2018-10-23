package com.android.haule.kotlinandroidexample.views.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.haule.kotlinandroidexample.R
import com.android.haule.kotlinandroidexample.controllers.MainController

class MainActivity : AppCompatActivity() {
    private var controller: MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        controller = MainController(this)
    }

    override fun onResume() {
        super.onResume()
        controller!!.callApi()
    }
}
