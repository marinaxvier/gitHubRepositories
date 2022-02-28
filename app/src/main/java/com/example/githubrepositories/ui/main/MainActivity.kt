package com.example.githubrepositories.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.databinding.ActivityMainBinding
import com.example.githubrepositories.model.ResponseModel
import com.example.githubrepositories.ui.main.viewModel.RepositoriesListViewModel
import com.example.githubrepositories.ui.main.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RepositoriesListViewModel
    lateinit var responseModel: ResponseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(RepositoriesListViewModel::class.java)
        viewModel.getRepositories()

        viewModel.responseModel.observe(this, Observer {
            if (it.isSuccessful){
                responseModel = it.body()!!
            }
        })
    }
}