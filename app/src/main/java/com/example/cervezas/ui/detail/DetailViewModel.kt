package com.example.cervezas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Item
import com.example.usecases.FindItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val findItemByIdUseCase: FindItemByIdUseCase
) : ViewModel() {

    private val itemId: Int = savedStateHandle.get<Int>(ITEM_ID) ?: -1

    sealed class UiModel {
        data object Loading : UiModel()
        data class Content(val items: List<Item>) : UiModel()
    }

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() {
            if (_uiModel.value == null) findItem()
            return _uiModel
        }

    fun findItem() {
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            _uiModel.value = UiModel.Content(findItemByIdUseCase.invoke(itemId))
        }
    }

    companion object {
        const val ITEM_ID = "DetailActivity:item"
    }
}
