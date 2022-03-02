package com.example.githubrepositories.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositories.databinding.RvRepositoriesItemBinding
import com.example.githubrepositories.model.RepositoryData

class RepositoriesAdapter(private val repositories: ArrayList<RepositoryData>) :
    RecyclerView.Adapter<RepositoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {

        val view = RvRepositoriesItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return RepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun update(list: List<RepositoryData>){
        repositories.addAll(list)
        notifyDataSetChanged()
    }
}

class RepositoriesViewHolder(private val bindingItem: RvRepositoriesItemBinding) : RecyclerView.ViewHolder(bindingItem.root) {
    fun bind(repository: RepositoryData){
        bindingItem.tvRepositoryName.text = repository.name
        bindingItem.tvRepositoryAuthor.text = repository.owner.name
        bindingItem.tvStars.text = repository.stars
        bindingItem.tvForks.text = repository.forks

        Glide.with(bindingItem.root)
            .load(repository.owner.avatar)
            .centerCrop()
            .into(bindingItem.ivAuthorAvatar)
    }
}