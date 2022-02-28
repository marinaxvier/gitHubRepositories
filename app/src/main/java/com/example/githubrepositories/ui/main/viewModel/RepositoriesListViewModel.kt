package com.example.githubrepositories.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoriesListViewModel(repository: Repository) : ViewModel() {
    var responseModel: MutableLiveData<Response<ResponseModel>> = MutableLiveData()
    val repository = Repository()

    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            var response: Response<ResponseModel> = repository.getRepositories()
            responseModel.postValue(response)
        }
    }
}