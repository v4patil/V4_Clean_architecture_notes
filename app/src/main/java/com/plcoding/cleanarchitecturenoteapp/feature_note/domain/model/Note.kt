package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.ui.theme.BabyBlue
import com.plcoding.cleanarchitecturenoteapp.ui.theme.LightGreen
import com.plcoding.cleanarchitecturenoteapp.ui.theme.RedOrange
import com.plcoding.cleanarchitecturenoteapp.ui.theme.RedPink
import com.plcoding.cleanarchitecturenoteapp.ui.theme.Violet


@Entity
data class Note(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "color")
    val color: Int,

    @PrimaryKey
    val id: Int? = null
){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
