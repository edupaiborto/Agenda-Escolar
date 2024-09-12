package com.eduplus.listadetarefascompose.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eduplus.listadetarefascompose.data.Tarefa

@Composable
fun ListaDeTarefas(tarefas: List<Tarefa>, onEdit: (Tarefa) -> Unit, onDelete: (Tarefa) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tarefas.size) { index ->
            val tarefa = tarefas[index]
            TarefaItem(tarefa, onEdit = { onEdit(tarefa) }, onDelete = { onDelete(tarefa) })
        }
    }
}

@Composable
fun TarefaItem(tarefa: Tarefa, onEdit: () -> Unit, onDelete: () -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var newDescription by remember { mutableStateOf(tarefa.descricao) }

    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isEditing) {
                TextField(
                    value = newDescription,
                    onValueChange = { newDescription = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Editar tarefa") }
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = {
                        tarefa.descricao = newDescription
                        onEdit() // Chama a função de edição
                        isEditing = false
                    }) {
                        Text("Salvar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { isEditing = false }) {
                        Text("Cancelar")
                    }
                }
            } else {
                Text(text = tarefa.descricao)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = { isEditing = true }) {
                        Text("Editar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onDelete) {
                        Text("Excluir")
                    }
                }
            }
        }
    }
}