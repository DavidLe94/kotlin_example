package com.android.haule.kotlinandroidexample.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.haule.kotlinandroidexample.R
import com.android.haule.kotlinandroidexample.callbacks.ItemOnClickListener
import com.android.haule.kotlinandroidexample.models.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Hau Le on 2018-10-23.
 */
class OwnerAdapter(var context: Context,
                   var list: ArrayList<Item>,
                   var listener: ItemOnClickListener)
    : RecyclerView.Adapter<OwnerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        val mViewHolder = ViewHolder(view)
        view.setOnClickListener{
            listener.onClick(mViewHolder.adapterPosition)
        }
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDisplayName.text = list[position].owner!!.displayName
        holder.tvUserType.text = list[position].owner!!.userType
        Glide.with(context)
                .load(list[position].owner!!.profileImage)
                .apply(RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.imgProfile)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDisplayName: TextView = itemView.findViewById(R.id.tv_display_name)
        var tvUserType: TextView = itemView.findViewById(R.id.tv_user_type)
        var imgProfile: CircleImageView = itemView.findViewById(R.id.img_profile_image)
    }

    fun notifyData(list: ArrayList<Item>){
        this.list = list
        notifyDataSetChanged()
    }
}