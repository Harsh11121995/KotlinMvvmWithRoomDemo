package com.mantratec.mvvm_kotlin_demo.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mantratec.mvvm_kotlin_demo.R
import com.mantratec.mvvm_kotlin_demo.model.UserResponseModel

class EMPAdapter(val empList: List<UserResponseModel>) :  RecyclerView.Adapter<EMPAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(empList[position])
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CheckResult", "SetTextI18n")
        fun bindItems(empModel: UserResponseModel) {
            val ivAvatar = itemView.findViewById(R.id.ivAvatar) as ImageView
            val tvNameFull = itemView.findViewById(R.id.tvNameFull) as TextView
            val tvEmail = itemView.findViewById(R.id.tvEmail) as TextView
            val pbImage = itemView.findViewById(R.id.pbImage) as ProgressBar

            tvNameFull.text = empModel.first_name + " " + empModel.lastname
            tvEmail.text = empModel.email

            pbImage.visibility = View.VISIBLE

            Glide.with(itemView).load(empModel.avtar)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbImage.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbImage.visibility = View.GONE
                        return false
                    }
                }).into(ivAvatar)

        }
    }
}