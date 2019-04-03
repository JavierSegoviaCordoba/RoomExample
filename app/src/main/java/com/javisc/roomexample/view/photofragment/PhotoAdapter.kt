package com.javisc.roomexample.view.photofragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javisc.roomexample.R
import com.javisc.roomexample.datasource.database.entity.Photo
import com.javisc.roomexample.extension.View.ImageView.glideCrossFadeCircle
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotoAdapter : ListAdapter<Photo, PhotoAdapter.MovieViewHolder>(TaskDiffCallback()) {

    private var list: List<Photo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position))


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            with(itemView) {
                imageViewPhoto.glideCrossFadeCircle(photo.url)
                textViewTitle.text = context.getString(R.string.id_placeholder, photo.id.toString())
                textViewUrl.text = photo.title
            }
        }

    }

    override fun submitList(list: List<Photo>?) {
        super.submitList(list)
        this.list = list
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem == newItem

}