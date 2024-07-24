package com.example.ilmioristorante.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ilmioristorante.domain.usecase.SearchRestaurantUseCase
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRestaurantUseCase: SearchRestaurantUseCase,
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String>
        get() = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<RestaurantModel>>(PagingData.empty())
    val searchedImages : StateFlow<PagingData<RestaurantModel>>
        get() = _searchedImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchRestaurants(query: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            searchRestaurantUseCase.searchRestaurant(query).cachedIn(viewModelScope).collect{
                _searchedImages.emit(it)
            }
        }
    }
}