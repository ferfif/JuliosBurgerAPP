package com.juliosburger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.domain.usecase.GetModifierGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifierGroupsViewModel @Inject constructor(
    private val getModifierGroupsUseCase: GetModifierGroupsUseCase
) : ViewModel() {

    private val _modifierGroups = MutableStateFlow<List<ModifierGroup>>(emptyList())
    val modifierGroups: StateFlow<List<ModifierGroup>> = _modifierGroups.asStateFlow()

    fun loadModifierGroups(productId: String) {
        viewModelScope.launch {
            getModifierGroupsUseCase(productId).collect { _modifierGroups.value = it }
        }
    }
}
