package com.example.githubrepositories.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.R
import com.example.githubrepositories.data.repository.Repository
import com.example.githubrepositories.model.RepositoriesResponse
import com.example.githubrepositories.model.RepositoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoriesListViewModel(private val repository: Repository) : ViewModel() {
    private val _errorResponse: MutableLiveData<Int> = MutableLiveData()
    val errorResponse: LiveData<Int>
    get() = _errorResponse

    private val _repositoriesDataResponse: MutableLiveData<ArrayList<RepositoryData>> = MutableLiveData()
    val repositoriesResponse: LiveData<ArrayList<RepositoryData>>
    get() = _repositoriesDataResponse

    private var completeList: ArrayList<RepositoryData> = arrayListOf()


    fun getRepositories(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<RepositoriesResponse> = repository.getRepositories(page)

            if (response.isSuccessful && !response.body()?.repositoryList.isNullOrEmpty()){
                response.body()?.let {
                    completeList.addAll(it.repositoryList)
                }
                _repositoriesDataResponse.postValue(completeList)
            } else {
                _errorResponse.postValue(R.string.error_message)
            }
        }
    }
}