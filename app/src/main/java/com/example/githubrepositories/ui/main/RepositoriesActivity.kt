package com.example.githubrepositories.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.databinding.ActivityRepositoriesBinding
import com.example.githubrepositories.ui.main.viewModel.RepositoriesListViewModel
import com.example.githubrepositories.ui.main.viewModel.ViewModelFactory

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoriesBinding
    private lateinit var viewModel: RepositoriesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        //Refatorar
        viewModel = ViewModelProvider(this,viewModelFactory).get(RepositoriesListViewModel::class.java)
        viewModel.getRepositories()

        viewModel.repositoriesResponse.observe(this, Observer { repositories ->
            if (repositories.isSuccessful){
               val repositoriesResponse = repositories.body()
            }
        })
    }
}