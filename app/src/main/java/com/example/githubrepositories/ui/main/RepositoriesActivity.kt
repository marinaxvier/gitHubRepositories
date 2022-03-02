package com.example.githubrepositories.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.databinding.ActivityRepositoriesBinding
import com.example.githubrepositories.model.RepositoryData
import com.example.githubrepositories.ui.main.adapter.RepositoriesAdapter
import com.example.githubrepositories.ui.main.viewModel.RepositoriesListViewModel
import com.example.githubrepositories.ui.main.viewModel.ViewModelFactory

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoriesBinding
    private lateinit var viewModel: RepositoriesListViewModel
    lateinit var repository: Repository
    private var isLoading = true
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        //Refatorar
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RepositoriesListViewModel::class.java)
        viewModel.getRepositories(page)
        viewModel.repositoriesResponse.observe(this, { repositories ->
            if (repositories.isSuccessful && repositories.body()?.repositoryList != null) {
                setAdapter(repositories.body()!!.repositoryList)
            }
        })
    }

    private fun setAdapter(repositoriesList: List<RepositoryData>) {
        val mAdapter = RepositoriesAdapter(repositoriesList)
        val mLayoutManager = LinearLayoutManager(this)

        val mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = mLayoutManager.childCount
                val pastVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
                val total = mAdapter.itemCount

                if (isLoading) {
                    if (visibleItemCount + pastVisibleItem >= total) {
                        page++
                        getRepositories(page)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        }

        binding.rvRepositoriesList.apply {
             adapter = mAdapter
            layoutManager = mLayoutManager
            addOnScrollListener(mScrollListener)

        }

    }

    private fun getRepositories(page: Int) {
        viewModel.getRepositories(page)
    }
}