package com.example.cervezas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Item
import com.example.usecases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla principal de listado y búsqueda de cervezas.
 * Gestiona el estado de la UI (Cargando, Contenido y Navegación) emitiéndolo vía LiveData.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    /**
     * Estados posibles de la interfaz de usuario en la pantalla principal.
     */
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

    private var searchJob: Job? = null

    /** Carga inicial del catálogo. */
    fun refresh() {
        search(null)
    }

    /**
     * Ejecuta la búsqueda de cervezas cancelando cualquier petición previa en vuelo.
     *
     * @param query Término escrito por el usuario en el buscador.
     */
    fun search(query: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiModel.value = UiModel.Loading
            try {
                val items = getItemsUseCase.invoke(query)
                _uiModel.value = UiModel.Content(items)
            } catch (_: Exception) {
                _uiModel.value = UiModel.Content(emptyList())
            }
        }
    }

    /**
     * Notifica la selección de una cerveza para navegar a su detalle.
     */
    fun onItemClicked(item: Item) {
        _uiModel.value = UiModel.Navigation(item)
    }
}
