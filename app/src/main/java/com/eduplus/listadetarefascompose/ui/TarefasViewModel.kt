package com.eduplus.listadetarefascompose.ui

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eduplus.listadetarefascompose.data.Tarefa
import com.eduplus.listadetarefascompose.data.TarefaDao
import com.eduplus.listadetarefascompose.data.TarefaDatabase
import kotlinx.coroutines.launch

class TarefasViewModel(application: Application) : AndroidViewModel(application) {
    private val tarefaDao: TarefaDao = TarefaDatabase.getDatabase(application).tarefaDao()

    private var _tarefas = mutableStateListOf<Tarefa>()
    val tarefas: List<Tarefa> get() = _tarefas

    fun carregarTarefasPorData(data: String) {
        viewModelScope.launch {
            _tarefas.clear()
            _tarefas.addAll(tarefaDao.obterTarefasPorData(data))
        }
    }

    fun adicionarTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            tarefaDao.adicionarTarefa(tarefa)
            carregarTarefasPorData(tarefa.data)
        }
    }

    fun editarTarefa(tarefaAtualizada: Tarefa) {
        viewModelScope.launch {
            tarefaDao.editarTarefa(tarefaAtualizada)
            carregarTarefasPorData(tarefaAtualizada.data)
        }
    }

    fun excluirTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            tarefaDao.excluirTarefa(tarefa)
            carregarTarefasPorData(tarefa.data)
        }
    }
}
