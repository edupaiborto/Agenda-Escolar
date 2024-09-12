package com.eduplus.listadetarefascompose.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.eduplus.listadetarefascompose.data.Tarefa
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ListaDeTarefasScreen(tarefasViewModel: TarefasViewModel) {
    var novaTarefaDescricao by remember { mutableStateOf("") }
    var dataSelecionada by remember { mutableStateOf(getDataAtual()) }

    LaunchedEffect(dataSelecionada) {
        tarefasViewModel.carregarTarefasPorData(dataSelecionada)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // CalendarView
        CalendarView(
            onDateChange = { dataSelecionada = it }
        )

        // Campo para adicionar nova tarefa
        TextField(
            value = novaTarefaDescricao,
            onValueChange = { novaTarefaDescricao = it },
            label = { Text("Descrição da Tarefa") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Botão de adicionar tarefa
        Button(onClick = {
            if (novaTarefaDescricao.isNotBlank()) {
                tarefasViewModel.adicionarTarefa(
                    Tarefa(
                        descricao = novaTarefaDescricao,
                        data = dataSelecionada
                    )
                )
                novaTarefaDescricao = ""
            }
        }) {
            Text("Adicionar Tarefa")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de tarefas
        Text(text = "Tarefas do dia: $dataSelecionada")
        ListaDeTarefas(
            tarefas = tarefasViewModel.tarefas,
            onEdit = { tarefa -> tarefasViewModel.editarTarefa(tarefa) },
            onDelete = { tarefa -> tarefasViewModel.excluirTarefa(tarefa) }
        )
    }
}

fun getDataAtual(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date())
}

@Composable
fun CalendarView(onDateChange: (String) -> Unit) {
    // Use LocalContext.current directly inside AndroidView
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            CalendarView(ctx).apply {
                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val dataSelecionada = "$year-${month + 1}-$dayOfMonth"
                    onDateChange(dataSelecionada)
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
