package com.example.yumemi.app.ui.home

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.yumemi.data.net.GithubImpl
import com.example.yumemi.domain.model.Contributor
import com.example.yumemi.domain.result.GithubRespBase
import kotlinx.coroutines.launch

class HomeViewModel(val activity: FragmentActivity) : ViewModel() {

    private val loading_ = MutableLiveData<Unit>()
    val loading: LiveData<Unit> = loading_
    private val contributors_ = MutableLiveData<GithubRespBase>()
    val contributors: LiveData<GithubRespBase> = contributors_

    private val githubImpl = GithubImpl()
    fun requestContributors() {
        loading_.postValue(Unit)
        viewModelScope.launch {
            val respObj = githubImpl.contributors()
            contributors_.postValue(respObj)
        }
    }
}

class HomeViewModelFactory(private val activity: FragmentActivity) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
