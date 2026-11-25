package com.example.cervezas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Item
import com.example.usecases.FindItemsByNameUseCase
import com.example.usecases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val findItemsByNameUseCase: FindItemsByNameUseCase
) : ViewModel() {

    sealed class UiModel {
        data object Loading : UiModel()
        data class Content(val items: List<Item>) : UiModel()
        data class Navigation(val item: Item) : UiModel()
    }

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() {
            if (_uiModel.value == null) refresh()
            return _uiModel
        }

    fun refresh() {
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            _uiModel.value = UiModel.Content(getItemsUseCase.invoke())
        }
    }

    fun onItemClicked(item: Item) {
        _uiModel.value = UiModel.Navigation(item)
    }

    fun doSearch(query: String) {
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            _uiModel.value = UiModel.Content(findItemsByNameUseCase.invoke(query))
        }
    }

    fun cleanSearch() {
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            _uiModel.value = UiModel.Content(emptyList())
        }
    }
}
