package com.android.haule.kotlinandroidexample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hau Le on 2018-10-23.
 */
class Answer {
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null
    @SerializedName("backoff")
    @Expose
    var backoff: Int? = null
    @SerializedName("quota_max")
    @Expose
    var quotaMax: Int? = null
    @SerializedName("quota_remaining")
    @Expose
    var quotaRemaining: Int? = null
}
