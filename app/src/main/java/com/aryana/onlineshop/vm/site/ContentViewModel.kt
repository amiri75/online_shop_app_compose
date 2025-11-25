package com.aryana.onlineshop.vm.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.site.Content
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repository: ContentRepository,
) : ViewModel() {

    private val _content = MutableStateFlow<NetworkResult<List<Content>>>(NetworkResult.Loading())
    val content: StateFlow<NetworkResult<List<Content>>> = _content

    private val _contentById = MutableStateFlow<NetworkResult<List<Content>>>(NetworkResult.Loading())
    val contentById: StateFlow<NetworkResult<List<Content>>> = _contentById

    fun getContent() {
        viewModelScope.launch {
            _content.value = repository.getContent()
        }
    }

    fun getContentById(title: String) {
        viewModelScope.launch {
            _contentById.value = repository.getContentById(title)
        }
    }
}