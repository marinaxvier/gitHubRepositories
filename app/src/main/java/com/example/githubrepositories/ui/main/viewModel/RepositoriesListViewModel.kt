package com.example.githubrepositories.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.model.RepositoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoriesListViewModel(private val repository: Repository) : ViewModel() {
    private val _repositoriesResponse: MutableLiveData<Response<RepositoriesResponse>> = MutableLiveData()
    val repositoriesResponse: LiveData<Response<RepositoriesResponse>>
    get() = _repositoriesResponse


    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            val repositories: Response<RepositoriesResponse> = repository.getRepositories()
            _repositoriesResponse.postValue(repositories)
        }
    }
}