package com.aryana.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(
    private val repository: SliderRepository,
) : ViewModel() {

    private val _listSlider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val listSlider: StateFlow<NetworkResult<List<Slider>>> = _listSlider

    private val _sliderById = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val sliderById: StateFlow<NetworkResult<List<Slider>>> = _sliderById

    fun getSliders() {
        viewModelScope.launch {
            _listSlider.value = repository.getSliders()
        }
    }

    fun getSlidersById(id: Int) {
        viewModelScope.launch {
            _sliderById.value = repository.getSlidersById(id)
        }
    }
}