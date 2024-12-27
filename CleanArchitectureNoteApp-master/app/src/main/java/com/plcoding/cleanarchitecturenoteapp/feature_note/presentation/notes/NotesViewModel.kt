package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.constraintlayout.solver.state.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class NotesViewModel @Inject constructor(
        private val noteUseCases: NoteUseCases
) : ViewModel(){

        private val _state = MutableStateFlow(NotesState())
        val state = _state.asStateFlow()
         private var recentlyDeletedNote : Note? = null
        private var getNotesJob : Job? = null
        init {
            getNotes(NoteOrder.Date(OrderType.Descending))
        }

        fun onEvent(event: NotesEvent){
                when (event){
                        is NotesEvent.Order -> {
                                if(state.value.noteOrder::class == event.noteOrder::class &&
                                        state.value.noteOrder.orderType == event.noteOrder.orderType){
                                        return
                                }
                                getNotes(event.noteOrder)

                        }
                        is NotesEvent.DeleteNote -> {
                                viewModelScope.launch {
                                        noteUseCases.deleteNote(event.note)
                                        recentlyDeletedNote = event.note
                                }

                        }
                        is NotesEvent.RestoreNote -> {
                                viewModelScope.launch {
                                        noteUseCases.addnote(recentlyDeletedNote?: return@launch)
                                }

                        }
                        is NotesEvent.ToggleOrderSection -> {
                               _state.value = state.value.copy(
                                       isOrderSectionVisible = !state.value.isOrderSectionVisible
                               )
                        }

                }
        }

        private fun getNotes(noteOrder: NoteOrder){
                getNotesJob?.cancel()
                getNotesJob = noteUseCases.getNotes(noteOrder)
                        .onEach { notes ->
                                _state.value = state.value.copy(
                                        notes = notes,
                                        noteOrder = noteOrder
                                )
                        }
                        .launchIn(viewModelScope)
        }

}

