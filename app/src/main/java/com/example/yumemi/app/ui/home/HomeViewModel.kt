package com.example.yumemi.app.ui.home

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.yumemi.data.net.GithubImpl
import com.example.yumemi.domain.model.Contributor
import com.example.yumemi.domain.result.GithubRespBase
import kotlinx.coroutines.launch

class HomeViewModel(val activity: FragmentActivity) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
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
