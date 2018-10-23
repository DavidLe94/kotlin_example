package com.android.haule.kotlinandroidexample.api


import com.android.haule.kotlinandroidexample.models.Answer
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Hau Le on 2018-10-23.
 */
interface ApiServices {
    @GET("2.2/answers?order=desc&sort=activity&site=stackoverflow")
    fun getAnswerList(): Observable<Answer>

}
