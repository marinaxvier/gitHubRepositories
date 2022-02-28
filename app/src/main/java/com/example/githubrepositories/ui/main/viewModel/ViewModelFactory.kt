package com.example.githubrepositories.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepositories.data.repository.Repository

class ViewModelFactory (val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoriesListViewModel(repository) as T
    }
}