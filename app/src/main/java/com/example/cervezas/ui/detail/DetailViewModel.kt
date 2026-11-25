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

/**
 * ViewModel encargado de la pantalla de detalle de una cerveza.
 * Recupera el ID del extra de navegación almacenado en [SavedStateHandle] y solicita la información completa.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val findItemByIdUseCase: FindItemByIdUseCase
) : ViewModel() {

    private val itemId: Int = savedStateHandle.get<Int>(ITEM_ID) ?: -1

    /** Estados de la UI en la vista de detalle. */
    sealed class UiModel {
        data object Loading : UiModel()
        data class Content(val beer: Item?) : UiModel()
    }

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() {
            if (_uiModel.value == null) findItem()
            return _uiModel
        }

    /** Realiza la petición de datos de la cerveza. */
    fun findItem() {
        if (itemId == -1) {
            _uiModel.value = UiModel.Content(null)
            return
        }
        viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            try {
                val beer = findItemByIdUseCase.invoke(itemId)
                _uiModel.value = UiModel.Content(beer)
            } catch (_: Exception) {
                _uiModel.value = UiModel.Content(null)
            }
        }
    }

    companion object {
        const val ITEM_ID = "DetailActivity:item"
    }
}
