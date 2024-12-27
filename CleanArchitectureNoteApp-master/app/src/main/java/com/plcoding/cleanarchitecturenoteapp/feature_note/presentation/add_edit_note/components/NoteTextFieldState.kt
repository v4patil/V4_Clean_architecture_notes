package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.components

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val ind: String = "",
    val isHintVisible: Boolean = true
)
