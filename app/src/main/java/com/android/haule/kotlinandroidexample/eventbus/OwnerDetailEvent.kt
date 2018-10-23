package com.android.haule.kotlinandroidexample.eventbus

import com.android.haule.kotlinandroidexample.models.Item
import com.android.haule.kotlinandroidexample.models.Owner

/**
 * Created by Hau Le on 2018-10-23.
 */
class OwnerDetailEvent(private var owner: Item) {
    //getter
    fun getItem(): Item? {
        return owner
    }
}