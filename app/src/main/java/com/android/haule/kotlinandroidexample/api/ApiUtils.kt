package com.android.haule.kotlinandroidexample.api

/**
 * Created by Hau Le on 2018-10-23.
 */
object ApiUtils {
    private const val DEV = "https://api.stackexchange.com/"

    val apiService: ApiServices
        get() = RetrofitClient.getClient(DEV)!!.create(ApiServices::class.java)
}
