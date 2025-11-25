package com.aryana.onlineshop.vm.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.site.Blog
import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.site.BlogRepository
import com.aryana.onlineshop.repository.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val repository: BlogRepository,
) : ViewModel() {

    private val _blog = MutableStateFlow<NetworkResult<List<Blog>>>(NetworkResult.Loading())
    val blog: StateFlow<NetworkResult<List<Blog>>> = _blog

    private val _blogById = MutableStateFlow<NetworkResult<List<Blog>>>(NetworkResult.Loading())
    val blogById: StateFlow<NetworkResult<List<Blog>>> = _blogById

    fun getBlogs() {
        viewModelScope.launch {
            _blog.value = repository.getBlogs()
        }
    }

    fun getBlogsById(id: Long) {
        viewModelScope.launch {
            _blogById.value = repository.getBlogsById(id)
        }
    }
}