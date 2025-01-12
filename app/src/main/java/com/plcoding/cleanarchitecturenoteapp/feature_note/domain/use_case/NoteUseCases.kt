package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

class NoteUseCases (
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNote: AddNote,
    val getNote: GetNote
){
}