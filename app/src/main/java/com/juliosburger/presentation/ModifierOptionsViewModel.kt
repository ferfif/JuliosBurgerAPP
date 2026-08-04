package com.juliosburger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.usecase.GetModifierOptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifierOptionsViewModel @Inject constructor(
    private val getModifierOptionsUseCase: GetModifierOptionsUseCase
) : ViewModel() {

    private val _modifierOptions = MutableStateFlow<List<ModifierOption>>(emptyList())
    val modifierOptions: StateFlow<List<ModifierOption>> = _modifierOptions.asStateFlow()

    fun loadModifierOptions(groupId: String) {
        viewModelScope.launch {
            getModifierOptionsUseCase(groupId).collect { _modifierOptions.value = it }
        }
    }
}
