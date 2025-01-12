package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ){
        Row (
            modifier = modifier.fillMaxSize()
        ) {
          DefaultRadioButton(
              text = "Title",
              selected  = noteOrder is NoteOrder.Title,
              onSelected = { onOrderChange(NoteOrder.Title(noteOrder.orderType))}
          )
          Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected  = noteOrder is NoteOrder.Date,
                onSelected = { onOrderChange(NoteOrder.Date(noteOrder.orderType))}
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected  = noteOrder is NoteOrder.Color,
                onSelected = { onOrderChange(NoteOrder.Color(noteOrder.orderType))}
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        Row (
            modifier = modifier.fillMaxSize(),
        ){
            DefaultRadioButton(
                text = "Ascending",
                selected  = noteOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChange(noteOrder.copy(orderType = OrderType.Ascending))
                }
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected  = noteOrder.orderType is OrderType.Descending,
                onSelected = {
                    onOrderChange(noteOrder.copy(orderType = OrderType.Descending))
                }
            )
            Spacer(modifier = modifier.width(8.dp))
        }
    }
}