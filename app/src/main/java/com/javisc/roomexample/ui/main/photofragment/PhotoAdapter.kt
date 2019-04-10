package com.javisc.roomexample.ui.main.photofragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javisc.roomexample.R
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.ui.extension.glideCrossFadeCircle
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(inflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            with(itemView) {
                val imageUrl = "https://picsum.photos/400?image=${photo.id}"
                imageViewPhoto.glideCrossFadeCircle(imageUrl)
                textViewTitle.text = context.getString(R.string.id_placeholder, photo.id.toString())
                textViewUrl.text = photo.title
            }
        }

    }

}

class TaskDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem == newItem

}